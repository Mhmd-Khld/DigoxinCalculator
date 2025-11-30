package com.example.digoxincalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class KidneyDisordersActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kidney_disorders)

        // تفعيل زر الرجوع (Up Button) في شريط العنوان
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Kidney Disorders"
    }

    // التعامل مع الضغط على زر الرجوع في شريط العنوان
    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}