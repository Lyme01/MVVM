package com.exa.main.http

import com.exa.base.base.BaseRepository
import com.exa.base.http.StateLiveData
import com.exa.main.bean.WxArticleBean

class WxArticleRepository : BaseRepository() {

    private val mService by lazy { RetrofitClient.service }

    suspend fun fetchWxArticle(stateLiveData: StateLiveData<List<WxArticleBean>>) {
        executeResp(stateLiveData, mService::getWxArticle)
    }

    suspend fun fetchWxArticleError(stateLiveData: StateLiveData<List<WxArticleBean>>) {
        executeResp(stateLiveData, mService::getWxArticleError)
    }

}