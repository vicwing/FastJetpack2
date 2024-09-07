package com.aisier.architecture.base

import android.app.ProgressDialog
import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleRegistry
import com.aisier.architecture.ktx.toast
import com.aisier.network.SHOW_TOAST
import com.jeremyliao.liveeventbus.LiveEventBus

/**
 * <pre>
 * author : wutao
 * time   : 2021/6/18
 * desc   : 去掉类上面的泛型，因为反射会影响性能。并且优先选择组合而不是继承。
 * version: 1.3
</pre> *
 */
abstract class BaseActivity(@LayoutRes contentLayoutId: Int) : AppCompatActivity(contentLayoutId), IUiView {
//    override val lifecycle: Lifecycle
//        get() = getFielLifeCycle()

//    fun getFielLifeCycle(): Lifecycle {
//        return  LifecycleRegistry(this)
//    }

//    override fun getLifecycle(): Lifecycle {
//        return super.getLifecycle()
//    }
    override fun onCreate(savedInstanceState: Bundle?) {
//        ColorTheme().applyTheme(this)
        super.onCreate(savedInstanceState)
        observeUi()
    }
    
    private fun observeUi() {
        LiveEventBus.get<String>(SHOW_TOAST).observe(this) {
            toast(it)
        }
    }
    
    private var progressDialog: ProgressDialog? = null
    
    override fun showLoading() {
        if (progressDialog == null)
            progressDialog = ProgressDialog(this)
        progressDialog?.show()
    }
    
    override fun dismissLoading() {
        progressDialog?.takeIf { it.isShowing }?.dismiss()
    }
}