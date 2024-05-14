package com.example.mathapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

//Manpreet Kaur-1835162
class MainActivity : AppCompatActivity() {
    var editTextFirstName: EditText? = null
    var editTextLastName: EditText? = null
    var checkBoxThirdGrade: CheckBox? = null
    var buttonGo: Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        editTextFirstName = findViewById(R.id.editTextFirstName)
        editTextLastName = findViewById(R.id.editTextLastName)
        checkBoxThirdGrade = findViewById(R.id.checkBoxThirdGrade)
        buttonGo = findViewById(R.id.buttonGo)
        buttonGo?.setOnClickListener(View.OnClickListener {
            val firstName = editTextFirstName?.getText().toString().trim { it <= ' ' }
            val lastName = editTextLastName?.getText().toString().trim { it <= ' ' }
            val thirdGrade = checkBoxThirdGrade?.isChecked()
            if (firstName.isEmpty() && lastName.isEmpty() && !thirdGrade!!) {
                Toast.makeText(
                    this@MainActivity,
                    "Please enter your name or check the third grade option",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                // Pass first name, last name, and third grade status to WelcomeActivity
                val intent = Intent(this@MainActivity, WelcomeActivity::class.java)
                intent.putExtra("firstName", firstName)
                intent.putExtra("lastName", lastName)
                intent.putExtra("thirdGrade", thirdGrade)
                startActivity(intent)
            }
        })
    }
}
