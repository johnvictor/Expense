package com.jv.daily

import java.text.SimpleDateFormat
import java.util.*

class Common {
    fun getDate(): String {
        val c = Calendar.getInstance().time
        val df = SimpleDateFormat("dd-MM-yyyy")
        return df.format(c)
    }
}