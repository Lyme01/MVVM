package com.exa.base.http

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonToken
import com.google.gson.stream.JsonWriter
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class Api {

    private val retrofit:Retrofit
    companion object {
        @Volatile
        private var instance: Api? = null
        private var service: Api? = null
        var mContext: Context? = null
        //访问超时
        private const val TIMEOUT: Long = 60

        fun getInstance(): Api {
            return instance ?: synchronized(this) {
                instance ?: Api().also { instance = it }
            }
        }

        /**
         * 自定义TypeAdapter ,null对象将被解析成空字符串
         */
        val STRING: TypeAdapter<String?> = object : TypeAdapter<String?>() {
            override fun read(reader: JsonReader): String? {
                try {
                    if (reader.peek() == JsonToken.NULL) {
                        reader.nextNull()
                        // 原先是返回null，这里改为返回空字符串
                        return ""
                    }
                    return reader.nextString()
                } catch (e: Exception) {
                    e.printStackTrace()
                }
                return ""
            }

            override fun write(
                    writer: JsonWriter,
                    value: String?
            ) {
                try {
                    if (value == null) {
                        writer.nullValue()
                        return
                    }
                    writer.value(value)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }


    init {

        val gson = Gson().newBuilder()
            .setLenient()
            .serializeNulls()
            .create()

        retrofit = Retrofit.Builder()
            .baseUrl("https://www.wanandroid.com/")
            .client( getOkHttpClient())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

    }

    private fun getOkHttpClient(): OkHttpClient {
        // Retrofit是基于OkHttpClient的，可以创建一个OkHttpClient进行一些配置
        return OkHttpClient.Builder() //打印接口信息，方便接口调试
                .addInterceptor(HttpLoggingInterceptor(HttpLoggingInterceptor.Logger { message ->
                    Log.e("zcb", "OkHttp====Message:$message");
                }).setLevel(HttpLoggingInterceptor.Level.BODY))
                .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
//            .addInterceptor(RqInterceptor())
//            .addInterceptor(AddCookiesInterceptor())
//            .addInterceptor(SaveCookiesInterceptor())
                .readTimeout(TIMEOUT, TimeUnit.SECONDS)
                .build()
    }

    fun get(): ApiService {
        return retrofit.create(ApiService::class.java)
    }
}