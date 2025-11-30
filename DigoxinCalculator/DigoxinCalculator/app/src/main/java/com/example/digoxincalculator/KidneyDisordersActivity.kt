package com.example.digoxincalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem // قد تحتاجها

class KidneyDisordersActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kidney_disorders)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Kidney Disorders"
    }

    // التعامل مع الضغط على زر الرجوع في شريط العنوان
    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}