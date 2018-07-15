package com.jv.daily

import android.content.Context

class MyPreference(context: Context) {

    var PREFERENCE_NAME = "DAILY_PREFERENCE"
    var PREFERENCE_HOUSEID = "HOUSE_ID"
    var PREFERENCE_PHONE = "PHONE"

    var preference = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE)

    fun setHouseId(houseID: String) {
       var editor = preference.edit();
        editor.putString(PREFERENCE_HOUSEID, houseID)
        editor.apply()
    }

    fun setPhone(phone: String) {
        var editor = preference.edit()
        editor.putString(PREFERENCE_PHONE, phone)
        editor.apply()
    }

    fun getHouseId(): String {
        return preference.getString(PREFERENCE_HOUSEID, null)
    }

    fun getPhone(): String {
        return preference.getString(PREFERENCE_PHONE, null)
    }


}