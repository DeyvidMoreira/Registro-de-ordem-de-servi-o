package com.example.tellcom.view.activitys

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.telecom.databinding.ActivityMainBinding
import com.example.tellcom.service.constants.ConstantsDate
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.icOrders.setOnClickListener(this)
        binding.icNewOrder.setOnClickListener(this)
        binding.icScore.setOnClickListener(this)
        binding.icCalendar.setOnClickListener(this)


    }

    override fun onClick(v: View) {
        when (v.id) {

            binding.icOrders.id -> {
                val intent = Intent(applicationContext, OrderActivity::class.java)
                startActivity(intent)
            }

            binding.icScore.id -> {
                val intent = Intent(applicationContext, ScoreActivity::class.java)
                startActivity(intent)
            }

            binding.icNewOrder.id -> {
                val intent = Intent(applicationContext, FormOrderActivity::class.java)
                startActivity(intent)
            }

            binding.icCalendar.id -> {
                showDataPickerDialog()
            }

        }
    }

    //Abrindo o calendario
    private fun showDataPickerDialog() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            this,
            { _, selectedYear, selectedMouth, selectedDay ->
                val selectedDate = Calendar.getInstance()
                selectedDate.set(selectedYear, selectedMouth, selectedDay)
                //formatando a data igual ao database
                val formattedDate = SimpleDateFormat(ConstantsDate.DATE.FORMAT_DATE, Locale.getDefault())
                    .format(selectedDate.time)
                openOrdersActivity(formattedDate)
            },
            year, month, day
        )
        datePickerDialog.show()
    }

    //Exibe as ordens do dia selecionado
    private fun openOrdersActivity(date: String) {
        val intent = Intent(this, DateOrderActivity::class.java).apply {
            putExtra("SELECTED_DATE", date)
        }
        startActivity(intent)
    }

}