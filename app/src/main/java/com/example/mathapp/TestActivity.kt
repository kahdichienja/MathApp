package com.example.mathapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.GridLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

//Manpreet Kaur-1835162
class TestActivity : AppCompatActivity() {
    var textViewTestDescription: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)
        textViewTestDescription = findViewById(R.id.textViewTestDescription)
        val gridLayout = findViewById<GridLayout>(R.id.gridLayout)
        for (i in 1..10) {
            val button = Button(this)
            button.text = i.toString()
            val params = GridLayout.LayoutParams()
            params.width = GridLayout.LayoutParams.WRAP_CONTENT
            params.height = GridLayout.LayoutParams.WRAP_CONTENT
            params.setMargins(10, 10, 10, 10)
            button.layoutParams = params
            button.setOnClickListener { v ->
                val selectedNumber = (v as Button).text.toString().toInt()
                val intent = Intent(this@TestActivity, MultiplicationTestActivity::class.java)
                intent.putExtra("selectedNumber", selectedNumber)
                startActivity(intent)
            }
            gridLayout.addView(button)
        }
    }
}
