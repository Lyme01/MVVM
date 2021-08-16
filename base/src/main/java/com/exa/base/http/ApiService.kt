package com.exa.base.http

import com.exa.base.BaseBean.ArticleBean
import com.exa.base.BaseBean.BaseResp
import com.exa.base.BaseBean.DataX
import retrofit2.http.GET
import retrofit2.http.Path


/**
 * @Author yangtianfu
 * @CreateTime 2020/3/31 21:04
 * @Describe retrofit 使用协程定义api
 */

interface ApiService {

    /**
     * 使用协程进行网络请求
     */
//    @GET("article/top/json/")
//    suspend fun getTopArticle(): BaseResp<List<ArticleBean>>

    @GET("article/list/{page}/json")
    suspend fun getArticleList(@Path("page") page: Int = 0):BaseResp<ArticleBean<List<DataX>>>

}