package com.dev_akash.freedictionary.utils.network_utils

import android.util.Log
import retrofit2.Response

fun <T> Response<T>.validate(): Resource<T?> {
    return try {
        val response = this

        if (response.isSuccessful) {
            return Resource.Success(response.body())
        }

        if (response.code()==404)
            return Resource.Error("Found nothing\nPlease check the word")

        if (response.code() == 403) {
            Log.d("rkpsx7DictionaryTesting", "403 aaya")
            //Handle token expired case here
        }
        if (response.code() == 502) {
            Log.d("rkpsx7DictionaryTesting", "403 aaya")
            return Resource.Error(response.message())
            //handle bad gateway case here (server possibly offline or deploying)
        }

        Log.d("rkpsx7DictionaryTesting", "last error executed aaya <><> ${response.code()}")
        return Resource.Error(response.raw().code.toString())
    } catch (e: Exception) {
        Resource.Error(e.message ?: e.toString(), null)
    }
}