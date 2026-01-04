package com.example.bmicalculator

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.pow

class ResultActivity : AppCompatActivity() {

    lateinit var resultTextView: TextView
    lateinit var imageView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        resultTextView = findViewById<TextView>(R.id.textView)
        imageView = findViewById<ImageView>(R.id.imageView)

        val height = intent.getStringExtra("height")?.toIntOrNull() ?: 0
        val weight = intent.getStringExtra("weight")?.toIntOrNull() ?: 0
        val name = intent.getStringExtra("name")

        // 키가 0이면 계산을 할 수 없으므로 안전장치를 추가합니다.
        if (height == 0) {
            resultTextView.text = "잘못된 입력입니다."
            return
        }

        // BMI 계산
        val bmi = weight / (height / 100.0).pow(2.0)

        // 결과 표시
        when {
            bmi >= 35 -> resultTextView.text = "고도 비만"
            bmi >= 30 -> resultTextView.text = "2단계 비만"
            bmi >= 25 -> resultTextView.text = "1단계 비만"
            bmi >= 23 -> resultTextView.text = "과체중"
            bmi >= 18.5 -> resultTextView.text = "정상"
            else -> resultTextView.text = "저체중"
        }

        // 이미지 출력
        when{
            bmi >=23 ->
                imageView.setImageResource(
                    R.drawable.ic_baseline_sentiment_very_dissatistfied_24)
            bmi >18.5 ->
                imageView.setImageResource(
                    R.drawable.baseline_sentiment_satisfied_alt_24)
            else ->
                imageView.setImageResource(
                    R.drawable.ic_baseline_mood_bad_24)
        }

        Toast.makeText(this,"$name" +" : "+ "$bmi",Toast.LENGTH_SHORT).show()
    }
}
