package com.aisier.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.aisier.R
import com.aisier.architecture.base.BaseFragment
import com.aisier.databinding.HomeFragmentBinding
import com.dylanc.viewbinding.binding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator


class Homefragment2 : BaseFragment(R.layout.home_fragment) {
    private val binding: HomeFragmentBinding by binding()
    private lateinit var fragmentList :MutableList<Fragment>
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val tablayout = binding.tablayout
        tablayout.addTab(tablayout.newTab().setText("Tab1"))
        tablayout.addTab(tablayout.newTab().setText("Tab2"))
        tablayout.addTab(tablayout.newTab().setText("Tab3"))
        tablayout.addTab(tablayout.newTab().setText("Tab4"))
        tablayout.addTab(tablayout.newTab().setText("Tab5"))

        fragmentList = ArrayList<Fragment>()
        fragmentList.add(HomeContentfragment())
        fragmentList.add(HomeContentfragment())
        fragmentList.add(HomeContentfragment())
        fragmentList.add(HomeContentfragment())
        fragmentList.add(HomeContentfragment())

        // 创建ViewPager2所使用的适配器，FragmentStateAdapter抽象类的实现类对象
        // 创建ViewPager2所使用的适配器，FragmentStateAdapter抽象类的实现类对象
        val adapter: FragmentStateAdapter = object : FragmentStateAdapter(this) {
            override fun createFragment(position: Int): Fragment {
                return fragmentList.get(position)
            }

            override fun getItemCount(): Int {
                return fragmentList.size
            }
        }
        binding.vpMain.setAdapter(adapter); // 给ViewPager2设置适配器
        TabLayoutMediator(
            binding.tablayout,
            binding.vpMain,
            false,
            false
        ) { tab: TabLayout.Tab, position: Int ->  // TabLayout和ViewPager2关联到一起
            tab.setText("123"); // 设置Tab的标题
//            tab.setCustomView(tabMenu(menuLogos.get(position), menus.get(position))) // 设置Tab的图标和标题
        }.attach() // 调用该方法才能真正绑定起来
    }

}