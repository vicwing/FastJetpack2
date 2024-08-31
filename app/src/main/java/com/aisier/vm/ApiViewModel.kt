package com.aisier.vm

import android.util.Log
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.viewModelScope
import com.aisier.architecture.base.BaseViewModel
import com.aisier.bean.User
import com.aisier.bean.WxArticleBean
import com.aisier.net.WxArticleRepository
import com.aisier.network.entity.ApiResponse
import com.aisier.network.observer.StateLiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * <pre>
 * @author : wutao
 * time   : 2019/08/17
 * desc   :
 * version: 1.0
</pre> *
 */
class ApiViewModel : BaseViewModel() {

    private val repository by lazy { WxArticleRepository() }

    val wxArticleLiveData = StateLiveData<List<WxArticleBean>>()
    val userLiveData = StateLiveData<User?>()
    private val dbLiveData = StateLiveData<List<WxArticleBean>>()
    private val apiLiveData = StateLiveData<List<WxArticleBean>>()
    val mediatorLiveDataLiveData = MediatorLiveData<ApiResponse<List<WxArticleBean>>>().apply {
        this.addSource(apiLiveData) {
            this.value = it
        }
        this.addSource(dbLiveData) {
            this.value = it
        }
    }

    fun requestNet() {
        viewModelScope.launch {
            wxArticleLiveData.value = repository.fetchWxArticleFromNet()
        }
    }

    fun requestNetError() {
        viewModelScope.launch {
            wxArticleLiveData.value = repository.fetchWxArticleError()
        }
    }

    /**
     * 多个数据源
     */
    fun requestFromNet() {
        Log.d("TAG", "主线程  之前 "+Thread.currentThread().name)
        viewModelScope.launch(Dispatchers.Main) {
            Log.d("TAG", "launch  executeHttp  之前 "+Thread.currentThread().name)
//            apiLiveData.value = repository.fetchWxArticleFromNet()
            sortList()
            Log.i("TAG", "launch  executeHttp  之后 "+Thread.currentThread().name)

        }
        Log.i("TAG", "主线程  after "+Thread.currentThread().name)
    }

    suspend fun sortList() = withContext(Dispatchers.IO) {
        Log.d("TAG", "launch  sortList  之前 "+Thread.currentThread().name)
        // Heavy work
        delay(5000)
    }
    fun requestFromDb() {
        viewModelScope.launch {
            dbLiveData.value = repository.fetchWxArticleFromDb()
        }
    }

    /**
     * 该请求使用Flow优化，自带loading。
     */
    fun login(username: String, password: String) {
        launchWithLoading(requestBlock = {
            repository.login(username, password)
        }, resultCallback = {
            userLiveData.value = it
        })
    }
}