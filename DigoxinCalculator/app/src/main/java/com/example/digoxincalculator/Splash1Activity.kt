package com.example.digoxincalculator
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity

class Splash1Activity : AppCompatActivity() {

    private val SPLASH_TIME_OUT: Long = 3000 // 3 ثواني

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen_1)

        // استخدام Handler لتأخير الانتقال إلى الشاشة التالية
        Handler(Looper.getMainLooper()).postDelayed({
            // بعد انتهاء الـ 3 ثواني، انتقل إلى الشاشة الثانية
            val intent = Intent(this, Splash2Activity::class.java)
            startActivity(intent)
            finish() // أغلق هذا الـ Activity حتى لا يمكن للمستخدم العودة إليه بزر الرجوع
        }, SPLASH_TIME_OUT)
    }
}