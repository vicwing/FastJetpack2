package com.aisier.architecture.base

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
//import androidx.lifecycle.LifecycleOwner
//import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import androidx.lifecycle.LifecycleRegistryOwner

//interface IUiView : LifecycleOwner {
interface IUiView : LifecycleOwner {
//    override val lifecycle: Lifecycle
//        get() =

    fun showLoading()

    fun dismissLoading()

}