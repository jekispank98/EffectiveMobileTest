package com.example.effectivemobiletest.helpers

import android.util.Patterns
import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.Locale

object InputData {

    fun checkEnteredMail(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    fun checkEnteredPhone(phoneNumber: String): Boolean {
        return phoneNumber.length == 18
    }

    fun getNumberFormat(sum: Int): String {
        val numberFormat = NumberFormat.getNumberInstance(Locale.getDefault())
        numberFormat.isGroupingUsed = true
        val symbols = (numberFormat as DecimalFormat).decimalFormatSymbols
        symbols.groupingSeparator = ' '
        numberFormat.decimalFormatSymbols = symbols
        return numberFormat.format(sum)
    }

    fun getRandomOrderNumber(): String {
        val random = (100000..999999).random()
        return random.toString()
    }
}