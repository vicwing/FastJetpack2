package com.aisier.ui

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.aisier.R
import com.aisier.adapter.MyViewPager2BottomAdapter
import com.aisier.architecture.base.BaseActivity
import com.aisier.databinding.ActivityHomeBottomNaviBinding
import com.aisier.ui.fragment.FirstFragment
import com.aisier.ui.fragment.Homefragment
import com.aisier.ui.fragment.SecondFragment
import com.dylanc.viewbinding.binding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView


class MainActivity : BaseActivity<ActivityHomeBottomNaviBinding>() {
//class MainActivity : BaseActivity(R.layout.activity_home) {

    private val TAG: String? = this::class.java.simpleName
//    private val binding: ActivityHomeBottomNaviBinding by binding()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
//        initViewPager()
    }


    /***
     *
     */
    private fun initView() {
        //让BottomNavigationView与NavController相关联
        //方法一：去fragment管理器里通过id找到NavHostFragment
//        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_vic) as NavHostFragment
//        val navController = navHostFragment.navController
//        navController.setGraph(R.navigation.nav_graph)
//        binding.bottomNavigationView.setupWithNavController(navController)
//        navController.addOnDestinationChangedListener { controller, destination, arguments ->
//
//            Log.d(TAG, "initView: ${destination.navigatorName}")
//        }

//        方法二：把androidx.fragment.app.FragmentContainerView改成fragment
//        val navController = findNavController(R.id.nav_host_fragment)
//        binding.bottomNavigationView.setupWithNavController(navController)


        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_vic) as? NavHostFragment
        val navController = navHostFragment!!.navController
        setupWithNavController(bottomNavigationView, navController)
//        binding.viewPager2.visibility = View.GONE
//        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_vic) as NavHostFragment
//        val navController = navHostFragment.findNavController()
//        navController.setGraph(R.navigation.nav_graph)
//        NavigationUI.setupWithNavController(binding.bottomNavigationView, navController)
//        binding.bottomNavigationView.setOnItemSelectedListener(object : NavigationBarView.OnItemSelectedListener {
//            override fun onNavigationItemSelected(item: MenuItem): Boolean {
//                Log.d(TAG, "onNavigationItemSelected: item ${item.title}")
//                when (item.itemId) {
//                    R.id.home_page -> {
//                        // 处理切换到Home Fragment的逻辑
//                        navController.navigate(R.id.home_page)
//                        return true
//                    }
//
//                    R.id.search_page -> {
//                        // 处理切换到Dashboard Fragment的逻辑
//                        navController.navigate(R.id.search_page)
//                        Log.d(TAG, "onNavigationItemSelected: 切换2 ")
//                        return true
//                    }
//
//                    R.id.share_page -> {
//                        // 处理切换到Notifications Fragment的逻辑
//                        navController.navigate(R.id.share_page)
//                        Log.d(TAG, "onNavigationItemSelected: 切换  3 ")
//                        return true
//                    }
//                }
//                return false
//            }
//        })
    }

    private fun initViewPager() {
        val myViewPager2BottomAdapter = MyViewPager2BottomAdapter(this, initFragmentList())
        binding.viewPager2.setAdapter(myViewPager2BottomAdapter)
        binding.viewPager2.isUserInputEnabled = false
        binding.bottomNavigationView.setOnItemSelectedListener(object : NavigationBarView.OnItemSelectedListener {
            override fun onNavigationItemSelected(item: MenuItem): Boolean {
                Log.d(TAG, "onNavigationItemSelected: item ${item.title}")
                when (item.itemId) {
                    R.id.home_page -> {
                        // 处理切换到Home Fragment的逻辑
                        //                        navController.navigate(R.id.home_page)
                        binding.viewPager2.setCurrentItem(0)
                        return true
                    }

                    R.id.search_page -> {
                        // 处理切换到Dashboard Fragment的逻辑
                        //                        navController.navigate(R.id.search_page)
                        binding.viewPager2.setCurrentItem(1)
                        Log.d(TAG, "onNavigationItemSelected: 切换2 ")
                        return true
                    }

                    R.id.share_page -> {
                        // 处理切换到Notifications Fragment的逻辑
                        //                        navController.navigate(R.id.share_page)
                        binding.viewPager2.setCurrentItem(2)
                        Log.d(TAG, "onNavigationItemSelected: 切换  3 ")
                        return true
                    }
                }
                return false
            }
        })
    }

    private fun initFragmentList(): List<Fragment> {
        var fragmentlist = ArrayList<Fragment>()
        fragmentlist.add(Homefragment())
        fragmentlist.add(FirstFragment())
        fragmentlist.add(SecondFragment())
        return fragmentlist
    }

}
