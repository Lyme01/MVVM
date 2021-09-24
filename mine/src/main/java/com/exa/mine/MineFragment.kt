package com.exa.mine

import com.exa.base.base.BaseVMFragment
import com.exa.mine.vm.MineVM
import com.example.home.R
import com.example.home.databinding.FragmentMineBinding

class MineFragment(override var mLayoutId: Int= R.layout.fragment_mine) :BaseVMFragment<FragmentMineBinding,MineVM>() {


    override fun onInitView() {
        ui.vm=createVM(MineVM::class.java)

    }
    override fun onCreateVM(vm: MineVM): MineVM {
        vm.tv.value="888"
        return super.onCreateVM(vm)
    }
}