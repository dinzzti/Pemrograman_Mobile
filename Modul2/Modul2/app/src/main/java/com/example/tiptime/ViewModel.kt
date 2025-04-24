package com.example.tiptime

import androidx.lifecycle.ViewModel
import java.text.NumberFormat
import kotlin.math.ceil

class TipViewModel : ViewModel() {
    var tipResult: String = ""

    fun calculateTip(cost: Double, tipPercent: Double, roundUp: Boolean): String {
        var tip = cost * tipPercent
        if (roundUp) tip = ceil(tip)

        tipResult = NumberFormat.getCurrencyInstance().format(tip)
        return tipResult
    }
}
