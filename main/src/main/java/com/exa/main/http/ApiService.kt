package com.exa.main.http

import com.exa.base.base.BaseBean
import com.exa.main.bean.WxArticleBean
import retrofit2.http.GET

interface ApiService {

    @GET("wxarticle/chapters/json")
    suspend fun getWxArticle(): BaseBean<List<WxArticleBean>>

    @GET("abc/chapters/json")
    suspend fun getWxArticleError(): BaseBean<List<WxArticleBean>>

    companion object {
        const val BASE_URL = "https://wanandroid.com/"
    }
}