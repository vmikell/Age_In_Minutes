package com.victormikell.agetominutescalculator

import android.app.DatePickerDialog
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //calling button by id & setting onClick method
        val button: Button = findViewById(R.id.btnDatePicker)
        button.setOnClickListener {view ->
            btnClick(view)
        }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun btnClick (view: View) {
        val myCalander = Calendar.getInstance()
        val year = myCalander.get(Calendar.YEAR)
        val month = myCalander.get(Calendar.MONTH)
        val day = myCalander.get(Calendar.DAY_OF_MONTH)
        DatePickerDialog(this,
                DatePickerDialog.OnDateSetListener {
                    view, selectedYear, selectedMonth, selectedDayOfMonth ->
//                    Toast.makeText(this,
//                            "The chosen year is $selectedYear, the month is $selectedMonth, and the day is $selectedDayOfMonth"
//                            , Toast.LENGTH_LONG).show()
                    //variable for chosen date in d/M/y format
                    val selectedDate = "${selectedMonth+1}/$selectedDayOfMonth/$selectedYear"
                    //setting text (setText method) in tvSelectedDate id
                    val tvSelecteddate: TextView = findViewById(R.id.tvSelectedDate)
                    tvSelecteddate.setText(selectedDate)

                    val sdf = SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH)

                    val theDate = sdf.parse(selectedDate)
                    /*
                    determine entered date in milliseconds since 1/1/1970 and divide by 60,000 to
                    convert to minutes
                     */
                    val dateInMinutes = theDate!!.time / 60000

                    val currentDate = sdf.parse(sdf.format((System.currentTimeMillis())))
                    /*
                    determine current date in milliseconds since 1/1/1970 and divide by 60,000 to
                    convert to minutes
                     */
                    val currentDateToMinutes = currentDate!!.time / 60000

                    val differenceInMinutes = currentDateToMinutes - dateInMinutes

                    val tvSelectedDateInMinutes: TextView = findViewById(R.id.tvSelectedDateinMinutes)
                    tvSelectedDateInMinutes.text = differenceInMinutes.toString()
                }
                , year
                , month
                , day).show()
    }
}