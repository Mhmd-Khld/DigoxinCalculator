package com.example.digoxincalculator

// Enums
enum class Gender { MALE, FEMALE }
enum class Diagnosis { CHF, AF, BOTH }
enum class Route { ORAL, IV }

// Data Class لمدخلات الحسابات
data class CalculationParams(
    val age: Int,
    val weight: Double,
    val height: Double,
    val creatinine: Double,
    val css: Double,
    val gender: Gender,
    val diagnosis: Diagnosis,
    val route: Route,
    val kRate: Double?,
    val interceptA: Double?,
    val tHalfA: Double?,
    val tHalfB: Double?
)


data class DoseResult(
    val dosingInfo: DosingInfo,
    val basicPk: BasicPkParameters,
    val clinicalPk: ClinicalPkParameters
)


data class DosingInfo(
    val patientWeight: String,
    val doseMg: String,
    val doseMcg: String,
    val loadingDoseMg: String,
    val ldStep1: String,
    val ldStep2: String,
    val ldStep3: String
)


data class BasicPkParameters(
    val totalCl: String,
    val vd: String,
    val ka: String,
    val ke: String,
    val ibw: String,
    val crCl: String
)


data class ClinicalPkParameters(
    val routeInfo: String,
    val halfLife: String,
    val auc: String,
    val tMax: String,
    val cMax: String
)