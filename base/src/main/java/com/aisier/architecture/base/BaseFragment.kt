package com.aisier.architecture.base

import android.app.ProgressDialog
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleRegistry
import com.aisier.architecture.anno.FragmentConfiguration

/**
 * author : wutao
 * time   : 2020/10/14
 * desc   :
 * version: 1.1
 */
abstract class BaseFragment(@LayoutRes contentLayoutId: Int) : Fragment(contentLayoutId), IUiView {
    override val lifecycle: Lifecycle
        get() = getFielLifeCycle()

    fun getFielLifeCycle(): Lifecycle {
        return  LifecycleRegistry(this)
    }

    private var useEventBus = false

    init {
        this.javaClass.getAnnotation(FragmentConfiguration::class.java)?.let {
            useEventBus = it.useEventBus
        }
    }

    private var progressDialog: ProgressDialog? = null

    override fun showLoading() {
        if (progressDialog == null)
            progressDialog = ProgressDialog(requireActivity())
        progressDialog?.show()
    }

    override fun dismissLoading() {
        progressDialog?.takeIf { it.isShowing }?.dismiss()
    }
}