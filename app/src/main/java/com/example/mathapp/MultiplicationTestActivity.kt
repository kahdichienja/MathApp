package com.example.mathapp

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.util.Collections

//Manpreet Kaur-1835162
class MultiplicationTestActivity : AppCompatActivity() {
    var textViewQuestion: TextView? = null
    var editTextAnswer: EditText? = null
    var buttonSubmit: Button? = null
    var selectedNumber = 0
    var totalQuestions = 10 // Total number of questions in the test
    var questionOrder: MutableList<Int>? = null // List to store the order of questions
    var questionCount = 0 // Keep track of the current question number
    var score = 0
    var sharedPreferences: SharedPreferences? = null
    var editor: SharedPreferences.Editor? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_multiplication_test)
        textViewQuestion = findViewById(R.id.textViewQuestion)
        editTextAnswer = findViewById(R.id.editTextAnswer)
        buttonSubmit = findViewById(R.id.buttonSubmit)
        sharedPreferences = getSharedPreferences("scores", MODE_PRIVATE)
        editor = sharedPreferences?.edit()

        // Retrieve selected number from intent
        selectedNumber = intent.getIntExtra("selectedNumber", 1)

        // Initialize question order and shuffle it
        questionOrder = ArrayList()
        for (i in 0 until totalQuestions) {
            questionOrder?.add(i)
        }
        Collections.shuffle(questionOrder)
        displayQuestion() // Display the first question
        buttonSubmit?.setOnClickListener(View.OnClickListener {
            val userAnswer = editTextAnswer?.getText().toString().trim { it <= ' ' }
            if (!userAnswer.isEmpty()) {
                val answer = userAnswer.toInt()
                val correctAnswer = selectedNumber * (questionOrder?.get(questionCount)!! + 1)
                if (answer == correctAnswer) {
                    score++
                    Toast.makeText(this@MultiplicationTestActivity, "Correct!", Toast.LENGTH_SHORT)
                        .show()
                } else {
                    Toast.makeText(
                        this@MultiplicationTestActivity,
                        "Incorrect!",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                // Proceed to the next question or finish the test
                questionCount++
                if (questionCount < totalQuestions) {
                    displayQuestion()
                } else {
                    // Finish the test and show the score
                    saveScore(score)
                    showFinalScore()
                }
            } else {
                Toast.makeText(
                    this@MultiplicationTestActivity,
                    "Please enter an answer",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }

    // Method to display the next question
    private fun displayQuestion() {
        // Get the index of the next question from the shuffled order
        val nextQuestionIndex = questionOrder!![questionCount]
        // Set the question text
        textViewQuestion!!.text =
            "Question " + (questionCount + 1) + ": What is " + selectedNumber + " x " + (nextQuestionIndex + 1) + "?"
        editTextAnswer!!.setText("") // Clear the previous answer
    }

    // Method to save the score
    private fun saveScore(score: Int) {
        val currentScore = sharedPreferences!!.getInt("score", 0)
        editor!!.putInt("score", currentScore + score)
        editor!!.apply()
    }

    // Method to show the final score
    private fun showFinalScore() {
        Toast.makeText(this@MultiplicationTestActivity, "Final Score: $score", Toast.LENGTH_SHORT)
            .show()
        // Reset question count and score for the next test
        questionCount = 0
        score = 0

        // Create an intent to navigate back to the previous activity
        val intent =
            Intent(this@MultiplicationTestActivity, MultiplicationOperationActivity::class.java)
        startActivity(intent)
        finish() // Finish the current activity
    }
}
