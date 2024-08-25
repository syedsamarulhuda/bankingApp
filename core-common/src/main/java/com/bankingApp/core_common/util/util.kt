package com.bankingApp.core_common.util

import android.annotation.SuppressLint
import java.text.SimpleDateFormat

@SuppressLint("SimpleDateFormat")
fun getDateTime(s: Long): String? {
    try {
        val sdf = SimpleDateFormat("dd-MM-yyyy, HH:mm")
        return sdf.format(s)
    } catch (e: Exception) {
        return e.toString()
    }
}

fun getAmountWithCurrency(strAmount: String) = "$ $strAmount"