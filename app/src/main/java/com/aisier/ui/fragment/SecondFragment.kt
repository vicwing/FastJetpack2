package com.aisier.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.aisier.R
import com.aisier.databinding.FragmentFirstBinding
import com.aisier.databinding.FragmentSecondBinding
import com.dylanc.viewbinding.binding

class SecondFragment : Fragment() {


    private val binding: FragmentSecondBinding by binding()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // 此处Fragment依然可以获取在Activity下设置的NavGraph中的值
        val map = findNavController().graph.arguments
        val activityArgument = map["arg"]
        val bundle = activityArgument?.defaultValue as? Bundle
        val text = bundle?.getString("text", "")
        println("这是从上层Activity中传递过来的值:${text}")
        // 此处获取从FirstFragment中传递的值
        val secondText = arguments?.getString("second", "")
        binding.mBtnFirst.text = secondText
        binding.mBtnFirst.setOnClickListener {
            // 点击返回上一层Fragment
//            findNavController().navigate(R.id.action_secondFragment_pop)
        }
    }

}
