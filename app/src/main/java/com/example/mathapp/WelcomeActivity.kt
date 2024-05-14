package com.example.mathapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

//Manpreet Kaur-1835162
class WelcomeActivity : AppCompatActivity() {
    var buttonMultiply: Button? = null
    var buttonAdd: Button? = null
    var buttonSubtract: Button? = null
    var textViewWelcome: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        // Retrieve first and last name from the previous activity
        val firstName = intent.getStringExtra("firstName")
        val lastName = intent.getStringExtra("lastName")
        textViewWelcome = findViewById(R.id.textViewWelcome)
        textViewWelcome?.setText("Hi $firstName $lastName")
        // Set the welcome message in big, bold, and chunky font
        buttonMultiply = findViewById(R.id.buttonMultiply)
        buttonAdd = findViewById(R.id.buttonAdd)
        buttonSubtract = findViewById(R.id.buttonSubtract)
        buttonMultiply?.setOnClickListener(View.OnClickListener {
            val intent = Intent(this@WelcomeActivity, MultiplicationOperationActivity::class.java)
            startActivity(intent)
        })
        buttonAdd?.setOnClickListener(View.OnClickListener {
            val intent = Intent(this@WelcomeActivity, AdditionTestActivity::class.java)
            startActivity(intent)
        })
        buttonSubtract?.setOnClickListener(View.OnClickListener {
            val intent = Intent(this@WelcomeActivity, SubtractionTestActivity::class.java)
            startActivity(intent)
        })
    }
}
