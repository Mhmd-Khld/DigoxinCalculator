package com.example.digoxincalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import android.view.MenuItem
import androidx.drawerlayout.widget.DrawerLayout
import androidx.core.view.GravityCompat
import android.content.Intent
import com.google.android.material.navigation.NavigationView
import androidx.appcompat.widget.Toolbar
import com.google.android.material.button.MaterialButtonToggleGroup
import com.google.android.material.button.MaterialButton
// ❌ تم حذف استدعاءات RadioGroup و RadioButton التي لم تعد مستخدمة
import android.os.Build
import android.view.WindowInsets
import android.view.WindowInsetsController
import android.view.WindowManager
import android.widget.ScrollView // ⭐️ يجب استيراد ScrollView

class MainActivity : AppCompatActivity() {

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navigationView: NavigationView
    private lateinit var toolbar: Toolbar

    private lateinit var ageEditText: EditText
    private lateinit var weightEditText: EditText
    private lateinit var heightEditText: EditText
    private lateinit var creatinineEditText: EditText
    private lateinit var cssEditText: EditText
    private lateinit var kRateEditText: EditText
    private lateinit var interceptAEditText: EditText
    private lateinit var tHalfAEditText: EditText
    private lateinit var tHalfBEditText: EditText

    // ⭐️ تعاريف MaterialButtonToggleGroup ⭐️
    // 1. الجنس
    private lateinit var genderToggleGroup: MaterialButtonToggleGroup
    private lateinit var maleButton: MaterialButton
    private lateinit var femaleButton: MaterialButton

    // 2. التشخيص
    private lateinit var diagnosisToggleGroup: MaterialButtonToggleGroup
    private lateinit var afButton: MaterialButton
    private lateinit var bothButton: MaterialButton
    private lateinit var chfButton: MaterialButton // تم إضافة زر CHF

    // 3. طريق الإعطاء
    private lateinit var routeToggleGroup: MaterialButtonToggleGroup
    private lateinit var ivButton: MaterialButton
    private lateinit var oralButton: MaterialButton // تم إضافة زر Oral

    private lateinit var calculateButton: Button
    private lateinit var errorTextView: TextView
    private lateinit var resultsLayout: LinearLayout
    private lateinit var resetButton: Button

    // ⭐️ إضافة تعريف ScrollView هنا ⭐️
    private lateinit var scrollView: ScrollView


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
    private lateinit var teamMemberButton: Button

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

        // ⭐️ ربط عناصر الجنس ⭐️
        genderToggleGroup = findViewById(R.id.genderToggleGroup)
        maleButton = findViewById(R.id.maleButton)
        femaleButton = findViewById(R.id.femaleButton)

        // ⭐️ ربط عناصر التشخيص ⭐️
        diagnosisToggleGroup = findViewById(R.id.diagnosisToggleGroup)
        afButton = findViewById(R.id.afButton)
        bothButton = findViewById(R.id.bothButton)
        chfButton = findViewById(R.id.chfButton)

        // ⭐️ ربط عناصر طريق الإعطاء ⭐️
        routeToggleGroup = findViewById(R.id.routeToggleGroup)
        ivButton = findViewById(R.id.ivButton)
        oralButton = findViewById(R.id.oralButton)

        calculateButton = findViewById(R.id.calculateButton)
        errorTextView = findViewById(R.id.errorTextView)
        resultsLayout = findViewById(R.id.resultsLayout)

        // ⭐️ ربط ScrollView هنا (يجب أن يكون ID الـ ScrollView في XML هو @+id/scrollView) ⭐️
        scrollView = findViewById(R.id.scrollView)

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

        teamMemberButton = navigationView.findViewById(R.id.teamMemberButton) // يجب أن يكون ID الزر في ملف القائمة هو teamMemberButton
        teamMemberButton.setOnClickListener {
            // ستحتاج لإنشاء هذا الـ Activity لاحقًا
            val intent = Intent(this, TeamMemberActivity::class.java)
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

            // ⭐️ اللوجيك المعدل للجنس ⭐️
            val selectedGender = if (genderToggleGroup.checkedButtonId == R.id.maleButton) Gender.MALE else Gender.FEMALE

            // ⭐️ اللوجيك المعدل للتشخيص ⭐️
            val selectedDiagnosis = when (diagnosisToggleGroup.checkedButtonId) {
                R.id.afButton -> Diagnosis.AF
                R.id.bothButton -> Diagnosis.BOTH
                R.id.chfButton -> Diagnosis.CHF
                else -> Diagnosis.CHF // افتراض CHF كقيمة افتراضية إذا لم يتم الاختيار
            }

            // ⭐️ اللوجيك المعدل لطريق الإعطاء ⭐️
            val selectedRoute = if (routeToggleGroup.checkedButtonId == R.id.ivButton) Route.IV else Route.ORAL

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

                // 🚀 كود النزول التلقائي هنا 🚀
                scrollView.post {
                    scrollView.fullScroll(View.FOCUS_DOWN)
                }

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

        scrollView.post {
            scrollView.fullScroll(View.FOCUS_DOWN)
        }
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

        genderToggleGroup.uncheck(R.id.maleButton)
        genderToggleGroup.uncheck(R.id.femaleButton)

// إلغاء تحديد مجموعة التشخيص
        diagnosisToggleGroup.uncheck(R.id.afButton)
        diagnosisToggleGroup.uncheck(R.id.bothButton)
        diagnosisToggleGroup.uncheck(R.id.chfButton)

// إلغاء تحديد مجموعة طريق الإعطاء
        routeToggleGroup.uncheck(R.id.ivButton)
        routeToggleGroup.uncheck(R.id.oralButton)

        // 2. إعادة تعيين أزرار الاختيار (اختياري: يمكن تركها كما هي لتعكس آخر اختيار)
        // يمكن استخدام genderToggleGroup.clearCheck() إذا كنت تريد إلغاء التحديد

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