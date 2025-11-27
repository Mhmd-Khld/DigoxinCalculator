package com.example.digoxincalculator

import java.math.RoundingMode
import kotlin.math.abs
import kotlin.math.ln
import kotlin.math.exp
import kotlin.math.roundToInt

object CalculationService {

    private const val TAU_HOURS = 24.0

    fun calculateDigoxinDose(params: CalculationParams): DoseResult? {

        if (params.age <= 0 || params.weight <= 0 || params.height <= 0 || params.creatinine <= 0 || params.css <= 0) {
            return null
        }

        // *** التعديل هنا: F أصبحت 0.9
        //بدلاً من 0.7 للـ Oral ***
        val bioAvailForDose = if (params.route == Route.IV) 1.0 else 0.9

        // 2. IBW
        val ibw = calculateIBW(params.height, params.gender)

        // 3. CrCl
        val crCl = calculateCrCl(params.age, ibw, params.creatinine, params.gender)

        // 4. Cl non-renal
        val clNonRenal = when (params.diagnosis) {
            Diagnosis.CHF -> 20.0
            Diagnosis.BOTH -> 20.0
            Diagnosis.AF -> 40.0
        }

        // 5. Total Cl
        val totalCl_mL_min = (0.8 * crCl) + clNonRenal
        val totalCl_L_hr = totalCl_mL_min * 0.06

        // 6. Dose (using F=0.9)
        val maintenanceDoseMcg = (params.css * totalCl_L_hr * TAU_HOURS) / bioAvailForDose
        val maintenanceDoseMg = maintenanceDoseMcg / 1000.0

        // 7. Vd & K
        var finalVd = 0.0
        var finalK = 0.0

        if (params.route == Route.IV) {
            // IV
            finalVd = (maintenanceDoseMcg * 1.0) / params.css
            if (finalVd > 0) finalK = totalCl_L_hr / finalVd
        } else {
            // Oral (Here F is also 0.9 per your request for both simple and complex calculation)
            if (params.interceptA > 0 && params.tHalfA > 0 && params.tHalfB > 0) {
                val Ka = 0.693 / params.tHalfA
                val K_elim = 0.693 / params.tHalfB
                val D0 = maintenanceDoseMcg
                val A = params.interceptA
                val denominator = A * (Ka - K_elim)

                if (abs(denominator) > 0.000001) {
                    finalVd = (bioAvailForDose * Ka * D0) / denominator
                }
                finalK = K_elim
            } else {
                finalVd = (maintenanceDoseMcg * bioAvailForDose) / params.css
                if (finalVd > 0) finalK = totalCl_L_hr / finalVd
            }
        }

        val displayKa = if (params.tHalfA > 0) 0.693 / params.tHalfA else 0.0
        val displayKe = if (params.tHalfB > 0) 0.693 / params.tHalfB else 0.0
        if (params.route == Route.ORAL && params.tHalfB > 0) {
            finalK = displayKe
        }

        // Loading Dose
        val loadingDoseMcg = (finalVd * params.css) / bioAvailForDose
        val loadingDoseMg = loadingDoseMcg / 1000.0

        val ldStep1 = loadingDoseMg * 0.5
        val ldStep2 = loadingDoseMg * 0.25
        val ldStep3 = loadingDoseMg * 0.25

        val halfLife = if (finalK > 0) 0.693 / finalK else 0.0

        var auc = 0.0
        var tMax = 0.0
        var cMax = 0.0

        if (params.route == Route.IV) {
            if (finalK > 0) auc = params.css / finalK
        } else {
            if (finalK > 0 && finalVd > 0) {
                auc = (bioAvailForDose * maintenanceDoseMcg) / (finalK * finalVd)
            }
            val k_a = displayKa
            val k_e = finalK
            if (k_a > 0 && k_e > 0 && k_a != k_e) {
                tMax = ln(k_a / k_e) / (k_a - k_e)
                val term1 = (bioAvailForDose * maintenanceDoseMcg * k_a) / (finalVd * (k_a - k_e))
                val term2 = exp(-k_e * tMax) - exp(-k_a * tMax)
                cMax = term1 * term2
            }
        }

        // Display text for route
        val routeName = if (params.route == Route.IV) "IV (F=1.0)" else "Oral (F=0.9)"

        // --- \\
        val dosingInfo = DosingInfo(
            patientWeight = String.format("%.1f kg", params.weight),
            doseMg = String.format("%.4f", maintenanceDoseMg),
            doseMcg = maintenanceDoseMcg.roundToInt().toString(),
            loadingDoseMg = String.format("%.3f mg", loadingDoseMg),
            ldStep1 = String.format("• 50%% Initially: %.3f mg", ldStep1),
            ldStep2 = String.format("• 25%% after 6 hrs: %.3f mg", ldStep2),
            ldStep3 = String.format("• 25%% after 12 hrs: %.3f mg", ldStep3)
        )

        val basicPk = BasicPkParameters(
            totalCl = String.format("%.2f L/hr", totalCl_L_hr),
            vd = String.format("%.2f L", finalVd),
            ka = if (displayKa > 0) String.format("%.4f hr⁻¹", displayKa) else "N/A",
            ke = if (displayKe > 0) String.format("%.4f hr⁻¹", displayKe) else "N/A",
            ibw = String.format("%.1f kg", ibw),
            crCl = String.format("%.2f mL/min", crCl)
        )

        val clinicalPk = ClinicalPkParameters(
            routeInfo = routeName,
            halfLife = String.format("%.1f hrs", halfLife),
            auc = String.format("%.2f mcg*hr/L", auc),
            tMax = if (tMax > 0) String.format("%.2f hrs", tMax) else "N/A (Oral only)",
            cMax = if (cMax > 0) String.format("%.2f mcg/L", cMax) else "N/A (Oral only)"
        )

        return DoseResult(dosingInfo, basicPk, clinicalPk)
    }

    private fun calculateIBW(heightInCm: Double, gender: Gender): Double {
        val heightInInches = heightInCm / 2.54
        val heightOver60 = heightInInches - 60.0
        if (heightOver60 <= 0) return if (gender == Gender.MALE) 50.0 else 45.5
        return if (gender == Gender.MALE) 50.0 + (2.3 * heightOver60) else 45.5 + (2.3 * heightOver60)
    }

    private fun calculateCrCl(age: Int, ibw: Double, creatinine: Double, gender: Gender): Double {
        if (creatinine <= 0) return 0.0
        var crCl = ((140 - age) * ibw) / (72 * creatinine)
        if (gender == Gender.FEMALE) crCl *= 0.85
        return crCl
    }
}