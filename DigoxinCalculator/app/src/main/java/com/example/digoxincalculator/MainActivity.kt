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
import android.view.MenuItem
import androidx.drawerlayout.widget.DrawerLayout
import androidx.core.view.GravityCompat
import android.content.Intent // استيراد Intent لفتح Activity جديدة

class MainActivity : AppCompatActivity() {

    // ============ 1. تعريف عناصر القائمة الجانبية ============
    private lateinit var drawerLayout: DrawerLayout

    // ============ 2. تعريف عناصر حقول الإدخال والـ Radio Groups ============
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

    // ============ 3. تعريف عناصر عرض النتائج ============
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

    // ============ تعريف الزر الجديد "ملاحظات" ============
    private lateinit var notesButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // ============ A. ربط القائمة الجانبية وتفعيل زرها ============
        drawerLayout = findViewById(R.id.drawer_layout)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        // يجب أن تتأكد من وجود ic_menu.xml في drawable
        // إذا كان لديك الخطأ، قم بإزالته مؤقتاً أو أضف الملف
        // supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_menu)
        supportActionBar?.title = "" // إخفاء العنوان الافتراضي لـ Activity

        // ============ B. ربط جميع عناصر واجهة المستخدم ============
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

        // ربط عناصر عرض النتائج
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

        // ============ ربط الزر الجديد "ملاحظات" وإضافة الـ Listener له ============
        notesButton = findViewById(R.id.notesButton)
        notesButton.setOnClickListener {
            val intent = Intent(this, NotesActivity::class.java)
            startActivity(intent)
        }
    }

    // ============ C. دالة فتح وإغلاق القائمة الجانبية ============
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                // فتح وإغلاق القائمة الجانبية من جهة اليمين (END)
                if (drawerLayout.isDrawerOpen(GravityCompat.END)) {
                    drawerLayout.closeDrawer(GravityCompat.END)
                } else {
                    drawerLayout.openDrawer(GravityCompat.END)
                }
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun calculateDose() {
        try {
            // التحقق من الحقول المطلوبة وتحويلها
            val age = ageEditText.text.toString().toInt()
            val weight = weightEditText.text.toString().toDouble()
            val height = heightEditText.text.toString().toDouble()
            val creatinine = creatinineEditText.text.toString().toDouble()
            val css = cssEditText.text.toString().toDouble()

            // الحقول الاختيارية يمكن أن تكون null
            val kRate = kRateEditText.text.toString().toDoubleOrNull()
            val interceptA = interceptAEditText.text.toString().toDoubleOrNull()
            val tHalfA = tHalfAEditText.text.toString().toDoubleOrNull()
            val tHalfB = tHalfBEditText.text.toString().toDoubleOrNull()

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
                // عرض النتائج باستخدام الأسماء الموحدة DoseResult
                resultWeightTextView.text = "Patient Weight: ${result.dosingInfo.patientWeight}"
                resultMcgTextView.text = "${result.dosingInfo.doseMcg} mcg"
                resultMgTextView.text = "(${result.dosingInfo.doseMg} mg)"
                resultLdTextView.text = result.dosingInfo.loadingDoseMg
                resultLdStep1.text = result.dosingInfo.ldStep1
                resultLdStep2.text = result.dosingInfo.ldStep2
                resultLdStep3.text = result.dosingInfo.ldStep3

                // النتائج الأساسية (BasicPkParameters)
                resultTotalClTextView.text = "Total Cl: ${result.basicPk.totalCl}"
                resultVdTextView.text = "Vd: ${result.basicPk.vd}"
                resultKaTextView.text = "Ka: ${result.basicPk.ka}"
                resultKeTextView.text = "Ke: ${result.basicPk.ke}"
                resultIbwTextView.text = "IBW: ${result.basicPk.ibw}"
                resultCrClTextView.text = "CrCl: ${result.basicPk.crCl}"

                // النتائج السريرية (ClinicalPkParameters)
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
            showError("Please fill in all required fields with valid numbers.")
        }
    }

    private fun showError(message: String) {
        errorTextView.text = message
        errorTextView.visibility = View.VISIBLE
        resultsLayout.visibility = View.GONE
    }
}