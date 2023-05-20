package com.dev_akash.freedictionary.utils

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.dev_akash.freedictionary.utils.preferences.SharedPrefs

object AppUtils {
    var isDarkMode by mutableStateOf(
        SharedPrefs.getBooleanParam(
            SharedPrefs.IS_DARK_MODE,
            false
        )
    )

    fun toggleTheme() {
        val finalValue = !isDarkMode
        isDarkMode = finalValue
        SharedPrefs.setBooleanParam(SharedPrefs.IS_DARK_MODE, finalValue)

    }

}