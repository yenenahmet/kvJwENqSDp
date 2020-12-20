package com.example.kvjwenqsdp.utils

import android.util.Log
import com.google.gson.JsonSyntaxException
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException

object NetworkUtil {

        fun failMessage(t: Throwable): String {
            when (t) {
                is HttpException -> {
                    val response = t.response()
                    Log.e("Response Code =", response.code().toString())
                       if (response.code() == 401) {
                         return "Kimlik doğrulanamadı!"
                    }
                    return "Bir hata oluştu!"
                }
                is SocketTimeoutException -> return "Bağlantı zaman aşımına uğradı."
                is IOException -> return "Lütfen internet bağlantınızı kontrol edin! Bağlantı kurulamadı."
                is JsonSyntaxException -> return "Yapısal bir sorun oluştu!"
                else -> return "Bir hata oluştu!"
            }
        }

}