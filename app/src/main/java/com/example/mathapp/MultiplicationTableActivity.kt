package com.example.mathapp

import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.view.Gravity
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

//Manpreet Kaur-1835162
class MultiplicationTableActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_multiplication_table)
        val tableLayout = findViewById<TableLayout>(R.id.tableLayout)

        // Create multiplication table rows and columns
        for (i in 1..10) {
            val tableRow = TableRow(this)
            for (j in 1..10) {
                val textView = TextView(this)
                textView.text = (i * j).toString()
                textView.setPadding(16, 16, 16, 16)

                // Set text color to black for the cells
                textView.setTextColor(Color.BLACK)

                // Set text style to bold for the first row and first column
                if (i == 1 || j == 1) {
                    textView.setTypeface(null, Typeface.BOLD)
                }

                // Set background color for the boxes
                if (i == 1 && j == 1) {
                    textView.setBackgroundColor(Color.LTGRAY)
                } else if (i == 1 || j == 1) {
                    textView.setBackgroundColor(Color.LTGRAY)
                }
                textView.gravity = Gravity.CENTER

                // Add TextView to TableRow
                tableRow.addView(textView)
            }
            tableLayout.addView(tableRow)
        }
    }
}
