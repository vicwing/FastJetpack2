package com.aisier.ui.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import com.aisier.Constatn
import com.aisier.R
import com.aisier.architecture.base.BaseFragment
import com.aisier.databinding.HomeContentFragmentBinding
import com.chad.library.adapter4.BaseQuickAdapter
import com.chad.library.adapter4.viewholder.QuickViewHolder
import com.dylanc.viewbinding.binding
import java.util.ArrayList
import java.util.logging.Logger


class HomeContentfragment : BaseFragment(R.layout.home_content_fragment) {
    private val TAG: String? = this::class.java.simpleName
    private val binding: HomeContentFragmentBinding by binding()
    private var title: String? = null
    private var index: String? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        title = arguments?.getString(Constatn.FRAGMENT_BUNDLE_KEY_TITLE) ?: "默认标题"
        index = arguments?.getString(Constatn.FRAGMENT_BUNDLE_KEY_INDEX) ?: "-1"
        binding.tvContent.text = title



        val date = makeDate()
        val testAdapter = TestAdapter(this,date)
//        testAdapter.items = makeDate()
        binding.recycleview.adapter = testAdapter
        val linearLayoutManager = LinearLayoutManager(context)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        binding.recycleview.layoutManager = linearLayoutManager


//        testAdapter.addAll(date)
        Log.d(TAG, "onViewCreated: "+ date.size)
    }

    fun makeDate(): List<String> {
        val list = ArrayList<String>()
        for (i in 0..20) {
            list.add(i.toString())
        }
        return list
    }
}

class TestAdapter(val fragment: HomeContentfragment, val list: List<String>) : BaseQuickAdapter<String, QuickViewHolder>(list) {

    private val TAG: String? = this::class.java.simpleName

    override fun onCreateViewHolder(context: Context, parent: ViewGroup, viewType: Int): QuickViewHolder {
        // 返回一个 ViewHolder
        return QuickViewHolder(R.layout.layout_animation, parent)
    }

    override fun onBindViewHolder(holder: QuickViewHolder, position: Int, item: String?) {
        // 设置item数据
        holder.getView<TextView>(R.id.tv_list_title).text = item
//
//        if (fragment is HomeContentfragment) {
//
//        }

//        val homeContentfragment = context as? HomeContentfragment
//        Log.d(TAG, "onBindViewHolder:  homeContentfragment ${homeContentfragment}")
    }

}