package com.example.digoxincalculator

enum class Gender { MALE, FEMALE }
enum class Diagnosis { CHF, AF, BOTH }
enum class Route { ORAL, IV }

data class CalculationParams(
    val age: Int,
    val weight: Double,
    val height: Double,
    val creatinine: Double,
    val css: Double,
    val gender: Gender,
    val diagnosis: Diagnosis,
    val route: Route,
    val kRate: Double = 0.0,
    val interceptA: Double = 0.0,
    val tHalfA: Double = 0.0,
    val tHalfB: Double = 0.0
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