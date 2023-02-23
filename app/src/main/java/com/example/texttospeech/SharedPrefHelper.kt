package com.example.texttospeech

import android.content.Context
import android.preference.PreferenceManager

object SharedPrefHelper {

    fun saveLanguage(context: Context,Language : String) {
        PreferenceManager.getDefaultSharedPreferences(
            context
        ).edit()
            .putString("Language",Language)
            .apply()

    }
    fun saveSpeed(context: Context,Speed : String) {
        PreferenceManager.getDefaultSharedPreferences(
            context
        ).edit()
            .putString("Speed",Speed)
            .apply()

    }
    fun getLanguage(context: Context) : String? {
        return PreferenceManager.getDefaultSharedPreferences(
            context
        ).getString("Language","")
    }
    fun getSpeed(context: Context) : String? {
        return PreferenceManager.getDefaultSharedPreferences(
            context
        ).getString("Speed","")

    }

}