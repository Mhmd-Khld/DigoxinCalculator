package com.example.digoxincalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class TeamMemberActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // ربط هذا النشاط بملف التصميم XML
        setContentView(R.layout.activity_team_member)

        // (اختياري) إضافة سهم العودة للخلف
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Team Member"
    }

    // (اختياري) دالة للتعامل مع الضغط على زر العودة في الـ ActionBar
    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}