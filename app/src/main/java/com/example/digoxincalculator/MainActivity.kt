package com.example.digoxincalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private lateinit var ageEditText: EditText
    private lateinit var weightEditText: EditText
    private lateinit var heightEditText: EditText
    private lateinit var creatinineEditText: EditText
    private lateinit var cssEditText: EditText
    private lateinit var kRateEditText: EditText
    private lateinit var interceptAEditText: EditText
    private lateinit var tHalfAEditText: EditText
    private lateinit var tHalfBEditText: EditText
    private lateinit var genderRadioGroup: RadioGroup
    private lateinit var maleRadioButton: RadioButton
    private lateinit var diagnosisRadioGroup: RadioGroup
    private lateinit var afRadioButton: RadioButton
    private lateinit var bothRadioButton: RadioButton
    private lateinit var routeRadioGroup: RadioGroup
    private lateinit var ivRadioButton: RadioButton
    private lateinit var calculateButton: Button
    private lateinit var errorTextView: TextView
    private lateinit var resultsLayout: LinearLayout

    private lateinit var resultWeightTextView: TextView
    private lateinit var resultMgTextView: TextView
    private lateinit var resultMcgTextView: TextView
    private lateinit var resultLdTextView: TextView
    private lateinit var resultLdStep1: TextView
    private lateinit var resultLdStep2: TextView
    private lateinit var resultLdStep3: TextView

    private lateinit var resultTotalClTextView: TextView
    private lateinit var resultVdTextView: TextView
    private lateinit var resultKaTextView: TextView
    private lateinit var resultKeTextView: TextView
    private lateinit var resultIbwTextView: TextView
    private lateinit var resultCrClTextView: TextView

    private lateinit var resultRouteInfoTextView: TextView
    private lateinit var resultHalfLifeTextView: TextView
    private lateinit var resultAucTextView: TextView
    private lateinit var resultTmaxTextView: TextView
    private lateinit var resultCmaxTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ageEditText = findViewById(R.id.ageEditText)
        weightEditText = findViewById(R.id.weightEditText)
        heightEditText = findViewById(R.id.heightEditText)
        creatinineEditText = findViewById(R.id.creatinineEditText)
        cssEditText = findViewById(R.id.cssEditText)
        kRateEditText = findViewById(R.id.kRateEditText)
        interceptAEditText = findViewById(R.id.interceptAEditText)
        tHalfAEditText = findViewById(R.id.tHalfAEditText)
        tHalfBEditText = findViewById(R.id.tHalfBEditText)
        genderRadioGroup = findViewById(R.id.genderRadioGroup)
        maleRadioButton = findViewById(R.id.maleRadioButton)
        diagnosisRadioGroup = findViewById(R.id.diagnosisRadioGroup)
        afRadioButton = findViewById(R.id.afRadioButton)
        bothRadioButton = findViewById(R.id.bothRadioButton)
        routeRadioGroup = findViewById(R.id.routeRadioGroup)
        ivRadioButton = findViewById(R.id.ivRadioButton)
        calculateButton = findViewById(R.id.calculateButton)
        errorTextView = findViewById(R.id.errorTextView)
        resultsLayout = findViewById(R.id.resultsLayout)

        resultWeightTextView = findViewById(R.id.resultWeightTextView)
        resultMgTextView = findViewById(R.id.resultMgTextView)
        resultMcgTextView = findViewById(R.id.resultMcgTextView)
        resultLdTextView = findViewById(R.id.resultLdTextView)
        resultLdStep1 = findViewById(R.id.resultLdStep1)
        resultLdStep2 = findViewById(R.id.resultLdStep2)
        resultLdStep3 = findViewById(R.id.resultLdStep3)

        resultTotalClTextView = findViewById(R.id.resultTotalClTextView)
        resultVdTextView = findViewById(R.id.resultVdTextView)
        resultKaTextView = findViewById(R.id.resultKaTextView)
        resultKeTextView = findViewById(R.id.resultKeTextView)
        resultIbwTextView = findViewById(R.id.resultIbwTextView)
        resultCrClTextView = findViewById(R.id.resultCrClTextView)

        resultRouteInfoTextView = findViewById(R.id.resultRouteInfoTextView)
        resultHalfLifeTextView = findViewById(R.id.resultHalfLifeTextView)
        resultAucTextView = findViewById(R.id.resultAucTextView)
        resultTmaxTextView = findViewById(R.id.resultTmaxTextView)
        resultCmaxTextView = findViewById(R.id.resultCmaxTextView)

        calculateButton.setOnClickListener {
            calculateDose()
        }
    }

    private fun calculateDose() {
        try {
            val age = ageEditText.text.toString().toInt()
            val weight = weightEditText.text.toString().toDouble()
            val height = heightEditText.text.toString().toDouble()
            val creatinine = creatinineEditText.text.toString().toDouble()
            val css = cssEditText.text.toString().toDouble()
            val kRate = kRateEditText.text.toString().toDoubleOrNull() ?: 0.0
            val interceptA = interceptAEditText.text.toString().toDoubleOrNull() ?: 0.0
            val tHalfA = tHalfAEditText.text.toString().toDoubleOrNull() ?: 0.0
            val tHalfB = tHalfBEditText.text.toString().toDoubleOrNull() ?: 0.0

            val selectedGender = if (maleRadioButton.isChecked) Gender.MALE else Gender.FEMALE
            val selectedDiagnosis = when (diagnosisRadioGroup.checkedRadioButtonId) {
                R.id.afRadioButton -> Diagnosis.AF
                R.id.bothRadioButton -> Diagnosis.BOTH
                else -> Diagnosis.CHF
            }
            val selectedRoute = if (ivRadioButton.isChecked) Route.IV else Route.ORAL

            val params = CalculationParams(
                age, weight, height, creatinine, css, selectedGender, selectedDiagnosis, selectedRoute,
                kRate, interceptA, tHalfA, tHalfB
            )

            val result = CalculationService.calculateDigoxinDose(params)

            if (result != null) {
                // Dosing Info
                resultWeightTextView.text = "Patient Weight: ${result.dosingInfo.patientWeight}"
                resultMcgTextView.text = "${result.dosingInfo.doseMcg} mcg"
                resultMgTextView.text = "(${result.dosingInfo.doseMg} mg)"
                resultLdTextView.text = result.dosingInfo.loadingDoseMg
                resultLdStep1.text = result.dosingInfo.ldStep1
                resultLdStep2.text = result.dosingInfo.ldStep2
                resultLdStep3.text = result.dosingInfo.ldStep3

                // Basic PK
                resultTotalClTextView.text = "Total Cl: ${result.basicPk.totalCl}"
                resultVdTextView.text = "Vd: ${result.basicPk.vd}"
                resultKaTextView.text = "Ka: ${result.basicPk.ka}"
                resultKeTextView.text = "Ke: ${result.basicPk.ke}"

                // Clinical PK
                resultRouteInfoTextView.text = "Route: ${result.clinicalPk.routeInfo}"
                resultHalfLifeTextView.text = "Half-Life (t1/2): ${result.clinicalPk.halfLife}"
                resultAucTextView.text = "AUC: ${result.clinicalPk.auc}"
                resultTmaxTextView.text = "Tmax: ${result.clinicalPk.tMax}"
                resultCmaxTextView.text = "Cmax: ${result.clinicalPk.cMax}"

                resultsLayout.visibility = View.VISIBLE
                errorTextView.visibility = View.GONE
            } else {
                showError("Please enter valid numbers > 0")
            }

        } catch (e: NumberFormatException) {
            showError("Please fill in all required fields")
        }
    }

    private fun showError(message: String) {
        errorTextView.text = message
        errorTextView.visibility = View.VISIBLE
        resultsLayout.visibility = View.GONE
    }
}