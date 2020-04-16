package com.bb.myrecipeapp.util

import android.util.Log
import com.bb.myrecipeapp.util.Constants.Companion.ERROR_PREFIX
import com.bb.myrecipeapp.util.Constants.Companion.TAG
import java.lang.Exception

class DebugLogger {
    companion object{
        fun logDebug(message: String){
            Log.d(TAG, message)
        }

        fun logError(exception: Exception){
            Log.d(TAG, ERROR_PREFIX + exception.localizedMessage)
        }
    }
}