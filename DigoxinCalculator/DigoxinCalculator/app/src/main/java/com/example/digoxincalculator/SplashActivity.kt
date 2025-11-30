package com.example.digoxincalculator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash) // ربط تصميم SplashActivity

        // التأكد من أن الانتقال يتم إلى MainActivity فقط
        Handler(Looper.getMainLooper()).postDelayed({
            // إنشاء نية للانتقال لـ MainActivity
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            // إغلاق شاشة البداية لمنع العودة لها
            finish()
        }, 3000) // 3 ثواني
    }
}