package com.jv.daily

import android.content.Intent
import android.content.BroadcastReceiver
import android.content.Context
import android.util.Log
import android.widget.Toast


class Reset : BroadcastReceiver() {

    //the method will be fired when the alarm is triggerred
    override fun onReceive(context: Context, intent: Intent) {
        Toast.makeText(context, "Resetting at 12 am", Toast.LENGTH_SHORT).show()
        var dbHelper = DatabaseHelper(context)
        dbHelper.clearMoney()
    }

}