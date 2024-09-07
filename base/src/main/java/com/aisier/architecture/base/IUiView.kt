package com.aisier.architecture.base

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
//import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import androidx.lifecycle.LifecycleRegistryOwner

//interface IUiView : LifecycleOwner {
interface IUiView : LifecycleOwner {

    fun showLoading()

    fun dismissLoading()

}