package com.example.bmicalculator

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import android.widget.EditText
import java.nio.charset.IllegalCharsetNameException

class MainActivity : AppCompatActivity() {
    lateinit var resultButton: Button
    lateinit var nameEditText: EditText
    lateinit var heightEditText: EditText
    lateinit var weightEditText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        resultButton = findViewById<Button>(R.id.resultButton)
        nameEditText = findViewById<EditText>(R.id.nameEditText)
        heightEditText = findViewById<EditText>(R.id.heightEditText)
        weightEditText = findViewById<EditText>(R.id.weightEditText)

        loadData()

        resultButton.setOnClickListener {
            // 값이 비어있는지 확인 (비어있으면 앱 종료 방지)
            val heightText = heightEditText.text.toString()
            val weightText = weightEditText.text.toString()

            if (heightText.isEmpty() || weightText.isEmpty()) {
                // 키나 몸무게가 비어있으면 아무것도 하지 않음 (또는 토스트 메시지)
                return@setOnClickListener
            }

            // 안전하게 저장 (toIntOrNull 사용 권장)
            saveData(heightText.toInt(), weightText.toInt())

            val intent = Intent(this, ResultActivity::class.java)
            intent.putExtra("height", heightText)
            intent.putExtra("weight", weightText)
            intent.putExtra("name",nameEditText.text.toString())
            startActivity(intent)
        }
    }

    private fun saveData(height: Int, weight: Int) {
        val pref = getPreferences(MODE_PRIVATE) // 0 대신 MODE_PRIVATE 권장
        val editor = pref.edit()

        editor.putString("KEY_NAME", nameEditText.text.toString())
        editor.putInt("KEY_HEIGHT", height)
        editor.putInt("KEY_WEIGHT", weight)
        editor.apply()
    }

    private fun loadData(){
        var pref = this.getPreferences(0)
        var name = pref.getString("KEY_NAME","")
        var height = pref.getInt("KEY_HEIGHT",0)
        var weight = pref.getInt("KEY_WEIGHT",0)

        if(name != "" && height != 0 && weight !=0){
            nameEditText.setText(name.toString())
            heightEditText.setText(height.toString())
            weightEditText.setText(weight.toString())
        }
    }
}