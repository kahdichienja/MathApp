package com.example.mathapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

//Manpreet Kaur-1835162
class MultiplicationOperationActivity : AppCompatActivity() {
    var buttonTestYourself: Button? = null
    var buttonTable: Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_multiplication_operation)
        buttonTestYourself = findViewById(R.id.buttonTestYourself)
        buttonTestYourself?.setOnClickListener(View.OnClickListener {
            val intent = Intent(this@MultiplicationOperationActivity, TestActivity::class.java)
            startActivity(intent)
        })
        buttonTable = findViewById(R.id.buttonTable)
        buttonTable?.setOnClickListener(View.OnClickListener {
            val intent = Intent(
                this@MultiplicationOperationActivity,
                MultiplicationTableActivity::class.java
            )
            startActivity(intent)
        })
    }
}
