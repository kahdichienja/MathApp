package com.example.mathapp

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.util.Collections

//Manpreet Kaur-1835162
class SubtractionTestActivity : AppCompatActivity() {
    var textViewQuestion: TextView? = null
    var radioGroupOptions: RadioGroup? = null
    var buttonSubmit: Button? = null
    var totalQuestions = 10 // Total number of questions in the test
    var questions: List<Question>? = null // List to store the questions
    var questionCount = 0 // Keep track of the current question number
    var score = 0
    var sharedPreferences: SharedPreferences? = null
    var editor: SharedPreferences.Editor? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_subtraction_test)
        textViewQuestion = findViewById(R.id.textViewQuestion)
        radioGroupOptions = findViewById(R.id.radioGroupOptions)
        buttonSubmit = findViewById(R.id.buttonSubmit)
        sharedPreferences = getSharedPreferences("scores", MODE_PRIVATE)
        editor = sharedPreferences?.edit()

        // Initialize questions
        questions = generateQuestions()
        displayQuestion() // Display the first question
        buttonSubmit?.setOnClickListener(View.OnClickListener {
            val selectedOptionId = radioGroupOptions?.getCheckedRadioButtonId()
            if (selectedOptionId != -1) {
                val selectedRadioButton = findViewById<RadioButton>(selectedOptionId!!)
                val selectedOption = selectedRadioButton.text.toString()
                val currentQuestion = questions!![questionCount]
                if (currentQuestion.correctAnswer == selectedOption) {
                    score++
                    Toast.makeText(this@SubtractionTestActivity, "Correct!", Toast.LENGTH_SHORT)
                        .show()
                } else {
                    Toast.makeText(this@SubtractionTestActivity, "Incorrect!", Toast.LENGTH_SHORT)
                        .show()
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
                    this@SubtractionTestActivity,
                    "Please select an answer",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }

    // Method to generate subtraction questions
    private fun generateQuestions(): List<Question> {
        val questions: MutableList<Question> = ArrayList()
        // Generate 10 random subtraction questions
        for (i in 0 until totalQuestions) {
            val num1 = (Math.random() * 100).toInt()
            val num2 = (Math.random() * 100).toInt()
            val correctAnswer = num1 - num2
            val options = generateOptions(correctAnswer)
            val question = Question(
                "Question " + (i + 1) + ": " + num1 + " - " + num2 + " = ?",
                options,
                correctAnswer.toString()
            )
            questions.add(question)
        }
        return questions
    }

    // Method to generate options for the multiple-choice question
    private fun generateOptions(correctAnswer: Int): List<String?> {
        val options: MutableList<String?> = ArrayList()
        options.add(correctAnswer.toString())
        // Generate three incorrect options
        while (options.size < 4) {
            val incorrectAnswer =
                correctAnswer + (Math.random() * 20).toInt() - 10 // Generate numbers close to the correct answer
            if (incorrectAnswer != correctAnswer) {
                options.add(incorrectAnswer.toString())
            }
        }
        Collections.shuffle(options)
        return options
    }

    // Method to display the next question
    private fun displayQuestion() {
        val currentQuestion = questions!![questionCount]
        textViewQuestion!!.text = currentQuestion.question
        radioGroupOptions!!.removeAllViews()
        for (option in currentQuestion.options) {
            val radioButton = RadioButton(this)
            radioButton.text = option
            radioGroupOptions!!.addView(radioButton)
        }
    }

    // Method to save the score
    private fun saveScore(score: Int) {
        val currentScore = sharedPreferences!!.getInt("score", 0)
        editor!!.putInt("score", currentScore + score)
        editor!!.apply()
    }

    // Method to show the final score and navigate back to the previous activity
    private fun showFinalScore() {
        Toast.makeText(this@SubtractionTestActivity, "Final Score: $score", Toast.LENGTH_SHORT)
            .show()
        // Reset question count and score for the next test
        questionCount = 0
        score = 0

        // Create an intent to navigate back to the previous activity
        val intent = Intent(this@SubtractionTestActivity, WelcomeActivity::class.java)
        startActivity(intent)
        finish() // Finish the current activity
    }
}
