package com.yaduo.yaduoplayer.request

import com.yaduo.common.applogic.AppLogicUtil
import kotlinx.coroutines.flow.flow
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * 网络请求管理器
 *
 * @author YaDuo
 * @since 2025-06-03 11:01:08
 */
object RequestManager {

    /** 网络请求接口实例 **/
    private val api: NetApi by lazy {
        createApi(NetApi::class.java)
    }

    /** 信任所有证书的SSLSocket实例 **/
    private val trustSSLSocket = TrustSSLSocket()

    /**
     * 获取媒体资源
     */
    fun getMediaSource() = flow { emit(api.getMediaSource()) }

    /**
     * 获取精选区分类
     */
    fun getSelectedCategories() = flow { emit(api.getSelectedCategories()) }

    /**
     * 创建网络请求接口
     * @return 接口实例
     */
    private fun <T> createApi(clazz: Class<T>): T {
        val client = OkHttpClient.Builder()
            .sslSocketFactory(
                trustSSLSocket.getSSLSocketFactory(),
                trustSSLSocket.getX509TrustManager()
            )
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(AppLogicUtil.getChuckerInterceptor())
            .hostnameVerifier(trustSSLSocket.getHostnameVerifier())
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl("http://mobilecdnbj.kugou.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

        return retrofit.create(clazz)
    }
}