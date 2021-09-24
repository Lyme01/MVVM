package com.exa.main

import com.exa.base.base.BaseVMFragment
import com.exa.main.databinding.FragmentHomeBinding
import com.exa.main.vm.ArticleViewModel

class HomeFragment:BaseVMFragment<FragmentHomeBinding,ArticleViewModel>() {
    override var mLayoutId: Int = R.layout.fragment_home
    override fun onInitView() {
        super.onInitView()
        ui.vm=createVM(ArticleViewModel::class.java)
    }

    override fun onCreateVM(vm: ArticleViewModel): ArticleViewModel {
        vm.tv.value="666"
        return super.onCreateVM(vm)
    }
}