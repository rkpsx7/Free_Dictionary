package com.dev_akash.freedictionary.utils.preferences

import android.content.Context
import android.content.SharedPreferences
import androidx.annotation.UiThread
import androidx.annotation.WorkerThread

object SharedPrefs {


    private const val PREFS_NAME = "QUOTE_BY_SHARED_PREF"
    private const val DEFAULT_STRING_VALUE = ""
    private const val DEFAULT_INT_VALUE = 0
    const val IS_DARK_MODE = "IS_DARK_MODE"

    private lateinit var prefs: SharedPreferences

    @WorkerThread
    fun loadAppPrefs(context: Context) {
        prefs = context.getSharedPreferences(PREFS_NAME, 0)
    }

    private val editor: SharedPreferences.Editor
        get() = prefs.edit()

    @WorkerThread
    fun setParamSync(key: String?, value: String?) {
        try {
            editor.putString(key, value).commit()
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
    }

    @UiThread
    fun setParam(key: String?, value: String?) {
        try {
            editor.putString(key, value).apply()
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
    }

    @WorkerThread
    fun clearAll() {
        // Clear everything but the gcm, referrer Strings, which will be required for create user
        editor.clear().commit()
    }

    fun getParam(key: String?): String? {
        return getParam(key, DEFAULT_STRING_VALUE)
    }

    fun getParam(key: String?, defVal: String?): String? {
        return prefs.getString(key, defVal)
    }

    fun getIntParam(key: String?, defaultValue: Int): Int {
        return prefs.getInt(key, defaultValue)
    }

    fun getIntParam(key: String?): Int {
        return getIntParam(key, DEFAULT_INT_VALUE)
    }

    fun getBooleanParam(key: String?, defValue: Boolean): Boolean {
        return prefs.getBoolean(key, defValue)
    }

    @JvmStatic
    @UiThread
    fun setBooleanParam(key: String?, value: Boolean) {
        try {
            editor.putBoolean(key, value).apply()
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
    }

    @WorkerThread
    fun setBooleanParamSync(key: String?, value: Boolean) {
        try {
            editor.putBoolean(key, value).commit()
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
    }

    @WorkerThread
    fun setIntParamSync(key: String?, value: Int) {
        try {
            editor.putInt(key, value).commit()
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
    }

    @UiThread
    fun setIntParam(key: String?, value: Int) {
        try {
            editor.putInt(key, value).apply()
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
    }

    @WorkerThread
    fun setLongParamSync(key: String?, value: Long) {
        try {
            editor.putLong(key, value).commit()
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
    }

    @UiThread
    fun setLongParam(key: String?, value: Long) {
        try {
            editor.putLong(key, value).apply()
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
    }

    fun getLongParam(key: String?): Long {
        return getLongParam(key, DEFAULT_INT_VALUE.toLong())
    }

    fun getLongParam(key: String?, defaultValue: Long): Long {
        return prefs.getLong(key, defaultValue)
    }

    fun hasParam(key: String?): Boolean {
        return prefs.contains(key)
    }

    fun clearParam(key: String?) {
        prefs.edit().remove(key).apply()
    }

    @UiThread
    fun deleteParam(key: String?) {
        try {
            editor.remove(key).apply()
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
    }

}