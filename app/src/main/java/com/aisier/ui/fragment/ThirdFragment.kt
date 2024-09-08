package com.aisier.ui.fragment

import android.os.Bundle
import android.view.View
import com.aisier.R
import com.aisier.architecture.base.BaseFragment

class ThirdFragment : BaseFragment(R.layout.frame_container_fragment) {
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        view.findViewById<View>(R.id.bt_api).setOnClickListener {
//            view.findNavController().navigate(R.id.netListFragment)
//        }
//        view.findViewById<Button>(R.id.bt_save_state).setOnClickListener {
//            view.findNavController().navigate(R.id.savedStateFragment)
//        }
//        view.findViewById<Button>(R.id.bt_change_theme).setOnClickListener {
//            requireActivity().startActivity(Intent(requireActivity(), SecondActivity::class.java))
//        }
    }

}