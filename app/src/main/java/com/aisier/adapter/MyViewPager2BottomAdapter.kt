package com.aisier.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter


internal class MyViewPager2BottomAdapter(fragmentActivity: FragmentActivity, list: List<Fragment>) :
    FragmentStateAdapter(fragmentActivity) {
    var fragmentList: List<Fragment>

    init {
        fragmentList = list
    }

    override fun createFragment(position: Int): Fragment {
        return fragmentList[position]
    }

    override fun getItemCount(): Int {
        return fragmentList.size
    }
}