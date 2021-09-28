package com.exa.main.vm

import androidx.lifecycle.viewModelScope
import com.exa.base.base.BaseVM
import com.exa.base.http.StateLiveData
import com.exa.main.bean.WxArticleBean
import com.exa.main.http.WxArticleRepository
import kotlinx.coroutines.launch

class MainViewModel : BaseVM() {

    private val repository by lazy { WxArticleRepository() }

    val wxArticleLiveData = StateLiveData<List<WxArticleBean>>()
    val wxArticleLoadingLiveData = StateLiveData<List<WxArticleBean>>()

    fun requestNet() {
        viewModelScope.launch {
            repository.fetchWxArticle(wxArticleLiveData)
        }
    }

    fun requestNetError() {
        viewModelScope.launch {
            repository.fetchWxArticleError(wxArticleLiveData)
        }
    }

    fun requestNetWithLoading() {
        viewModelScope.launch {
            repository.fetchWxArticle(wxArticleLoadingLiveData)
        }
    }
}