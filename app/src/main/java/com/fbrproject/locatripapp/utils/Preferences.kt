package com.fbrproject.locatrip.utils

import android.content.Context
import android.content.SharedPreferences

class Preferences(val context: Context) {
    companion object {
        const val USER_PREFF = "USER_PREFF, 0"
    }

    var sharedPreferences = context.getSharedPreferences(USER_PREFF, 0)

    fun setValues(key: String, value: String) {
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.putString(key, value)
        editor.apply()
    }

    fun setLongValues(key: String, value: Long) {
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.putLong(key, value)
        editor.apply()
    }

    fun getValues(key: String): String? {
        return sharedPreferences.getString(key, "")
    }

    fun getLongValues(key: String): Long {
        return sharedPreferences.getLong(key, 0L)
    }
}