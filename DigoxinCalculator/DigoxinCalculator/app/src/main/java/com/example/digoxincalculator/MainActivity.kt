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
import android.content.Intent
import com.google.android.material.navigation.NavigationView
import androidx.appcompat.widget.Toolbar // تم إضافة هذا الاستدعاء

class MainActivity : AppCompatActivity() {

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navigationView: NavigationView
    private lateinit var toolbar: Toolbar // تعريف المتغير للـ Toolbar

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
    private lateinit var resetButton: Button

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

    private lateinit var notesButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // ============ A. ربط القائمة الجانبية والـ Toolbar ============
        drawerLayout = findViewById(R.id.drawer_layout)
        navigationView = findViewById(R.id.nav_view)

        // ربط الـ Toolbar وتعيينه كـ ActionBar
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_menu)
        supportActionBar?.title = ""

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

        // ============ C. ربط عناصر عرض النتائج ============
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

        // ============ D. تعيين الـ Listener لزر Calculate ============
        calculateButton.setOnClickListener {
            calculateDose()
        }

        resetButton = findViewById(R.id.resetButton)
        resetButton.setOnClickListener {
            resetFields()
        }

        // ============ E. ربط زر "ملاحظات" ============
        notesButton = navigationView.findViewById(R.id.notesButton)
        notesButton.setOnClickListener {
            // تأكد من وجود NotesActivity في مشروعك وإلا سيتوقف التطبيق
            val intent = Intent(this, NotesActivity::class.java)
            startActivity(intent)
            drawerLayout.closeDrawer(GravityCompat.END)
        }
    }

    // ============ F. دالة التعامل مع الضغط على أزرار القائمة ============
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
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

    // ============ G. دالة حساب الجرعة ============
    private fun calculateDose() {
        try {
            val age = ageEditText.text.toString().toInt()
            val weight = weightEditText.text.toString().toDouble()
            val height = heightEditText.text.toString().toDouble()
            val creatinine = creatinineEditText.text.toString().toDouble()
            val css = cssEditText.text.toString().toDouble()

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
                resultWeightTextView.text = "Patient Weight: ${result.dosingInfo.patientWeight}"
                resultMcgTextView.text = "${result.dosingInfo.doseMcg} mcg"
                resultMgTextView.text = "(${result.dosingInfo.doseMg} mg)"
                resultLdTextView.text = result.dosingInfo.loadingDoseMg
                resultLdStep1.text = result.dosingInfo.ldStep1
                resultLdStep2.text = result.dosingInfo.ldStep2
                resultLdStep3.text = result.dosingInfo.ldStep3

                resultTotalClTextView.text = "Total Cl: ${result.basicPk.totalCl}"
                resultVdTextView.text = "Vd: ${result.basicPk.vd}"
                resultKaTextView.text = "Ka: ${result.basicPk.ka}"
                resultKeTextView.text = "Ke: ${result.basicPk.ke}"
                resultIbwTextView.text = "IBW: ${result.basicPk.ibw}"
                resultCrClTextView.text = "CrCl: ${result.basicPk.crCl}"

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


    private fun resetFields() {
        // 1. مسح جميع حقول الإدخال (TextInputEditText)
        ageEditText.text.clear()
        weightEditText.text.clear()
        heightEditText.text.clear()
        creatinineEditText.text.clear()
        cssEditText.text.clear()
        kRateEditText.text.clear()
        interceptAEditText.text.clear()
        tHalfAEditText.text.clear()
        tHalfBEditText.text.clear()

        // *ملاحظة*: لا نحتاج لـ findViewById هنا لأننا عرفنا المتغيرات في الأعلى (ageEditText, إلخ)

        // 2. إعادة تعيين أزرار الاختيار (Optional)
        // يمكنك اختيار إعادة تعيينها إلى الخيار الافتراضي إذا أردت
        // genderRadioGroup.clearCheck()

        // 3. مسح أو إعادة تعيين النتائج المعروضة (TextViews)
        resultWeightTextView.text = ""
        resultMgTextView.text = ""
        resultMcgTextView.text = ""
        resultLdTextView.text = ""
        resultLdStep1.text = ""
        resultLdStep2.text = ""
        resultLdStep3.text = ""

        resultTotalClTextView.text = ""
        resultVdTextView.text = ""
        resultKaTextView.text = ""
        resultKeTextView.text = ""
        resultIbwTextView.text = ""
        resultCrClTextView.text = ""

        resultRouteInfoTextView.text = ""
        resultHalfLifeTextView.text = ""
        resultAucTextView.text = ""
        resultTmaxTextView.text = ""
        resultCmaxTextView.text = ""

        // 4. إخفاء منطقة النتائج وإخفاء رسالة الخطأ
        resultsLayout.visibility = View.GONE
        errorTextView.visibility = View.GONE
    }

}