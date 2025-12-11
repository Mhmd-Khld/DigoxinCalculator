package com.example.digoxincalculator
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class Splash2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen_2)

        val nextButton: Button = findViewById(R.id.nextButton)

        nextButton.setOnClickListener {
            // عند الضغط على زر "التالي"، انتقل إلى الـ Activity الرئيسي لتطبيقك
            val intent = Intent(this, MainActivity::class.java) // استبدل MainActivity بالـ Activity الرئيسي الخاص بك
            startActivity(intent)
            finish() // أغلق هذا الـ Activity
        }
    }
}