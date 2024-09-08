package com.aisier.architecture.base

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.activity.ComponentActivity
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleRegistry
import androidx.viewbinding.ViewBinding
import com.aisier.architecture.ktx.toast
import com.aisier.network.SHOW_TOAST
import com.dylanc.viewbinding.base.ActivityBinding
import com.dylanc.viewbinding.base.ActivityBindingDelegate
import com.jeremyliao.liveeventbus.LiveEventBus
import java.lang.reflect.Field


/**
 * <pre>
 * author : wutao
 * time   : 2021/6/18
 * desc   : 去掉类上面的泛型，因为反射会影响性能。并且优先选择组合而不是继承。
 * version: 1.3
</pre> *
 */
abstract class BaseActivity<VB : ViewBinding>  : AppCompatActivity(), IUiView ,ActivityBinding<VB> by ActivityBindingDelegate() {
    override val lifecycle: Lifecycle
        get() = getLifecycleRegistryByReflection(this)

//    override val lifecycle: Lifecycle
//        get() = getLifecycle()

    // 获取mLifecycleRegistry的反射方法
//    @Throws(NoSuchFieldException::class, IllegalAccessException::class)
    open fun getLifecycleRegistryByReflection(myObject: ComponentActivity): LifecycleRegistry {
        val clazz: Class<*> = ComponentActivity::class.java
        val field: Field = clazz.getDeclaredField("mLifecycleRegistry")
        field.setAccessible(true)
        return field.get(myObject) as LifecycleRegistry
    }

    override fun onCreate(savedInstanceState: Bundle?) {
//        ColorTheme().applyTheme(this)
        super.onCreate(savedInstanceState)
        setContentViewWithBinding()
        observeUi()


        Log.d(TAG, "onCreate:  lifecycle.hashCode() ${ lifecycle.hashCode()}")
    }
    
    private fun observeUi() {
        LiveEventBus.get<String>(SHOW_TOAST).observe(this) {
            toast(it)
        }
    }

    override fun showLoading() {
    }

    override fun dismissLoading() {
    }
//    private var progressDialog: ProgressDialog? = null
//
//    override fun showLoading() {
//        if (progressDialog == null)
//            progressDialog = ProgressDialog(this)
//        progressDialog?.show()
//    }
//
//    override fun dismissLoading() {
//        progressDialog?.takeIf { it.isShowing }?.dismiss()
//    }
}

/**
 * 不适用反射
 */
abstract class BaseBindingActivity<VB : ViewBinding>(
    private val inflate: (LayoutInflater) -> VB,
) : AppCompatActivity() {

    lateinit var binding: VB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = inflate(layoutInflater)
        setContentView(binding.root)
    }
}