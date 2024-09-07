package com.aisier.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.aisier.R
import com.aisier.databinding.FragmentFirstBinding
import com.aisier.databinding.HomeFragmentBinding
import com.dylanc.viewbinding.binding

class FirstFragment : Fragment() {
    private val binding: FragmentFirstBinding by binding()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // 获取此前在Activity中设置的NavGraph中的参数
        val map = findNavController().graph.arguments
        val argument = map["arg"]
        val bundle = argument?.defaultValue  as? Bundle
        // 获取Bundle中的值
        val text = bundle?.getString("text", "默认值")
        // 设置Button显示的文字
         binding.btnSecon.text = text
        val secondBundle = Bundle()
        secondBundle.putString("second", "传递到下一个Fragment的值")
        // 跳转至第二个Fragment页面，并且传递参数
        binding.btnSecon.setOnClickListener {
//            findNavController().navigate(R.id.action_firstFragment_to_secondFragment, secondBundle)
        }

    }

}
