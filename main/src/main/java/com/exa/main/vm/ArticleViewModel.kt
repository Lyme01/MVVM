package com.exa.main.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.exa.base.BaseBean.DataX
import com.exa.base.base.BaseVM
import com.exa.base.http.Api
import kotlinx.coroutines.launch


/**
 * @Author yangtianfu
 * @CreateTime 2020/4/5 20:24
 * @Describe
 */
class ArticleViewModel :BaseVM() {
    private val _articleListData = MutableLiveData<List<DataX>>()
    //保证外部只能观察此数据，不同通过setValue修改 model调用articleListData拿到网络请求数据交个观察者，但是不能修改
    val articleListData: LiveData<List<DataX>> = _articleListData

    private val _errorMsg = MutableLiveData<String?>()
    val errorMsg: LiveData<String?> = _errorMsg
    var tv=MutableLiveData<String>()
    fun fetch(page:Int){
        viewModelScope.launch {
            var result = Api.getInstance().get().getArticleList(page)
            //请求到的数据用livedata包裹
            _articleListData.value = result.data.datas!!
        }


    }
}