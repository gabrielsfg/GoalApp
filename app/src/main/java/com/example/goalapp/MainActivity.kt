package com.example.goalapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.goalapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var binding: ActivityMainBinding? = null

    private val currencies = listOf("United States Dollar (USD) - $", "Euro (EUR) - €",
        "Japanese Yen (JPY) - ¥", "British Pound (GBP) - £", "Canadian Dollar (CAD) - C\$")

    private var selectedCurrency: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        binding?.currencyButton?.setOnClickListener {
            showCurrency()
        }

        binding?.startBtn?.setOnClickListener {
            if (selectedCurrency !=  null){
                val intent = Intent(this, GoalActivity::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Please Select a Currency!!",
                    Toast.LENGTH_SHORT).show()
            }

        }
    }

    private fun showCurrency(){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Select a Currency")
        builder.setItems(currencies.toTypedArray()){
            _, which -> selectedCurrency = currencies[which]
            binding?.currencyButton?.text = selectedCurrency
        }
        builder.show()
    }
}