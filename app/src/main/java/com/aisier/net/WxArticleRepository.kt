package com.aisier.net

import android.util.Log
import com.aisier.bean.User
import com.aisier.bean.WxArticleBean
import com.aisier.network.base.BaseRepository
import com.aisier.network.entity.ApiResponse
import com.aisier.network.entity.ApiSuccessResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext


class WxArticleRepository : BaseRepository() {

    private val TAG: String = WxArticleRepository::class.java.simpleName
    private val mService by lazy {
        RetrofitClient.service
    }

    suspend fun fetchWxArticleFromNet(): ApiResponse<List<WxArticleBean>> {
        Log.d(TAG, "fetchWxArticleFromNet: executeHttp  之前 "+Thread.currentThread().name)
        return executeHttp {
            delay(5000)
            Log.d(TAG, "fetchWxArticleFromNet: mService.login  之前 "+Thread.currentThread().name)
            mService.getWxArticle()
        }
    }

    suspend fun fetchWxArticleFromDb(): ApiResponse<List<WxArticleBean>> {
        return getWxArticleFromDatabase()
    }

    suspend fun fetchWxArticleError(): ApiResponse<List<WxArticleBean>> {
        return executeHttp {
            mService.getWxArticleError()
        }
    }

    suspend fun login(username: String, password: String): ApiResponse<User?> {
        Log.d(TAG, "login: executeHttp  之前 "+Thread.currentThread().name)
        return executeHttp {
            Log.d(TAG, "login: mService.login  之前 "+Thread.currentThread().name)
            mService.login(username, password)
//            Log.d(TAG, "login: mService.login  之后 "+Thread.currentThread().name)
        }
    }

    private suspend fun getWxArticleFromDatabase(): ApiResponse<List<WxArticleBean>> = withContext(Dispatchers.IO) {
        val bean = WxArticleBean()
        bean.id = 999
        bean.name = "零先生"
        bean.visible = 1
        ApiSuccessResponse(arrayListOf(bean))
    }


}