package com.example.connectapi.utils

import android.content.Context
import android.content.SharedPreferences

class AppPreferences(context: Context) {
    private val PREFS_NAME = "app_prefs"
    private val KEY_DARK_MODE = "dark_mode"
    private val sharedPrefs: SharedPreferences

    init {
        sharedPrefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    }
    fun saveDarkModeState(isDarkMode: Boolean) {
        sharedPrefs.edit().putBoolean(KEY_DARK_MODE, isDarkMode).apply()
    }
    fun getDarkModeState(): Boolean {
        return sharedPrefs.getBoolean(KEY_DARK_MODE, false)
    }
}