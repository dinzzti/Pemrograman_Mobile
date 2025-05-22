package com.example.tiptime

import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import java.text.NumberFormat
import kotlin.math.ceil
import androidx.activity.viewModels
import com.example.tiptime.TipViewModel


class MainActivity : AppCompatActivity() {
    private val viewModel: TipViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Thread.sleep(3000)
        installSplashScreen()
        setContentView(R.layout.activity_main)

        val costOfServiceEditText = findViewById<EditText>(R.id.cost_of_service_edit_text)
        val tipOptions = findViewById<RadioGroup>(R.id.tip_options)
        val roundUpSwitch = findViewById<Switch>(R.id.round_up_switch)
        val calculateButton = findViewById<Button>(R.id.calculate_button)
        val tipResult = findViewById<TextView>(R.id.tip_result)

        if (viewModel.tipResult.isNotEmpty()) {
            tipResult.text = "Tip Amount: ${viewModel.tipResult}"
        }

        calculateButton.setOnClickListener {
            val costInput = costOfServiceEditText.text.toString()
            val cost = costInput.toDoubleOrNull()

            if (costInput.isEmpty()) {
                tipResult.text = ""
                Toast.makeText(this, "Masukkan biaya layanan!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (cost == null || cost <= 0.0) {
                tipResult.text = ""
                Toast.makeText(
                    this,
                    "Input tidak valid. Masukkan angka yang benar!",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }

            val tipPercentage = when (tipOptions.checkedRadioButtonId) {
                R.id.amazing_option -> 0.20
                R.id.good_option -> 0.18
                R.id.okay_option -> 0.15
                else -> {
                    Toast.makeText(
                        this,
                        "Pilih kualitas layanan terlebih dahulu!",
                        Toast.LENGTH_SHORT
                    ).show()
                    return@setOnClickListener
                }
            }
            val result = viewModel.calculateTip(cost, tipPercentage, roundUpSwitch.isChecked)
            tipResult.text = "Tip Amount: $result"

            Log.d("TipTime", "Tip dihitung: $result")
        }
    }
}
