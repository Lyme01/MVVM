package com.exa.main

import android.content.Context
import com.exa.base.base.BaseVMFragment
import com.exa.main.bean.WxArticleBean
import com.exa.main.databinding.FragmentHomeBinding
import com.exa.main.vm.ArticleViewModel
import com.exa.main.vm.MainViewModel

class HomeFragment:BaseVMFragment<FragmentHomeBinding,ArticleViewModel>() {
    override var mLayoutId: Int = R.layout.fragment_home
    override var isDark: Boolean=true
    protected var mContext: Context? = null//activity的上下文对象

    override fun onInitView() {
        super.onInitView()
        ui.vm = createVM(ArticleViewModel::class.java)
        vm.wxArticleLiveData.observeState(this) {
            onSuccess { result: List<WxArticleBean>? ->
                result?.let {
                    val wxArticleBean: WxArticleBean = result[0]
                    ui.text.text =
                        wxArticleBean.name + "   是否展示： " + wxArticleBean.visible + "\n" + result[1].name + "   是否展示： " + result[1].visible

                }
            }



        }
    }

    override fun onCreateVM(vm: ArticleViewModel): ArticleViewModel {
        vm.requestNet()
        vm.requestNetError()
        vm.requestNetWithLoading()
        return super.onCreateVM(vm)
    }
}