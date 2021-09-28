package com.exa.mvvm


import android.view.View
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.exa.base.adapter.CommonFragmentPagerAdapter
import com.exa.base.base.BaseDBActivity
import com.exa.base.view.BottomNavView
import com.exa.main.HomeFragment
import com.exa.mine.MineFragment
import com.exa.mvvm.databinding.ActivityMainBinding
import java.util.ArrayList

class MainActivity(override var mLayoutId: Int=R.layout.activity_main) :
    BaseDBActivity<ActivityMainBinding>() ,BottomNavView.OnBottomViewItemSelectedListener{
    override var isDark: Boolean=false

    private var fragmentList: List<Fragment>? = null
    private var mTitleList: Array<String>?=null
    private var mSelectedIcon: IntArray?=null
    private var mUnSelectedIcon: IntArray?=null
    override fun onInitView() {
        super.onInitView()
        setEvents()
    }

    fun setEvents(){
        ui.mViewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {
                ui.bottomNav.setCurrentItem(position)
            }

            override fun onPageScrollStateChanged(state: Int) {}
        })
    }

    override fun onInitData() {
        fragmentList = ArrayList()
        mTitleList = arrayOf("首页", "发现", "我的")
        val also = intArrayOf(R.mipmap.home_icon, R.mipmap.contacts_icon, R.mipmap.my_icon).also { mUnSelectedIcon = it }
        mSelectedIcon = intArrayOf(
            R.mipmap.home_icon_1,
            R.mipmap.contacts_icon_1,
            R.mipmap.my_icon_1
        )

        (fragmentList as ArrayList<Fragment>)?.add(HomeFragment())
//        (fragmentList as ArrayList<Fragment>)?.add(FindFragment())
        (fragmentList as ArrayList<Fragment>)?.add(MineFragment())

        ui.mViewPager.adapter = CommonFragmentPagerAdapter(
            supportFragmentManager,
            fragmentList
        )

        ui.bottomNav
            .setItemViewResId(R.layout.menu_main_activity)
            .setmTextList(mTitleList)
            .setSelectedTextColor(resources.getColor(R.color.common_blue))
            .setNormalTextColor(resources.getColor(R.color.common_gray))
            .setNormalIconArray(mUnSelectedIcon)
            .setSelectedIconArray(mSelectedIcon)
            .setListener(this)
            .build()
    }
    override fun onItemSelected(view: View?, position: Int) {
        ui.bottomNav.setCurrentItem(position)
        ui.mViewPager.setCurrentItem(position, false)
    }


}