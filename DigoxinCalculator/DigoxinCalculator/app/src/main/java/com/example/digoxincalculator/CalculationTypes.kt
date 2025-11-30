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
    val kRate: Double?,       // تم التعديل للسماح بـ null
    val interceptA: Double?,  // تم التعديل للسماح بـ null
    val tHalfA: Double?,      // تم التعديل للسماح بـ null
    val tHalfB: Double?       // تم التعديل للسماح بـ null
)

// Data Class لنتائج الحسابات (النتيجة النهائية)
data class DoseResult(
    val dosingInfo: DosingInfo,
    val basicPk: BasicPkParameters,
    val clinicalPk: ClinicalPkParameters
)

// Data Class لنتائج الجرعة
data class DosingInfo(
    val patientWeight: String,
    val doseMg: String,
    val doseMcg: String,
    val loadingDoseMg: String,
    val ldStep1: String,
    val ldStep2: String,
    val ldStep3: String
)

// Data Class لمعلمات الحرائك الدوائية الأساسية
data class BasicPkParameters(
    val totalCl: String,
    val vd: String,
    val ka: String,
    val ke: String,
    val ibw: String,
    val crCl: String
)

// Data Class لمعلمات الحرائك الدوائية السريرية
data class ClinicalPkParameters(
    val routeInfo: String,
    val halfLife: String,
    val auc: String,
    val tMax: String,
    val cMax: String
)