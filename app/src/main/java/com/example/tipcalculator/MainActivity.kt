package com.example.tipcalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tipcalculator.databinding.ActivityMainBinding
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.buttonCalculate.setOnClickListener{ calculateTip()}
    }

    private fun calculateTip() {
        val cost  = binding.etCostOfService.text.toString().toDoubleOrNull()

        if(cost == null){
            binding.tipAmount.text = ""
            return
        }

        val tipPercentage = when(binding.tipOptions.checkedRadioButtonId){
            R.id.option_twenty_percentage -> 0.20
            R.id.option_eighteen_percent -> 0.18
            else -> 0.15
        }
        var tip = tipPercentage*cost

        if(binding.roundUpSwitch.isChecked){
            tip = kotlin.math.ceil(tip)
        }

        displayTip(tip)
    }

    private fun displayTip(tip: Double) {
        val formattedTip = NumberFormat.getCurrencyInstance().format(tip)
        binding.tipAmount.text = getString(R.string.tip_amount, formattedTip)
    }
}