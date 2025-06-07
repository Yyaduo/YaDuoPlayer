package com.yaduo.yaduoplayer.request

import com.yaduo.player.model.MediaItemData
import com.yaduo.yaduoplayer.model.SelectedCategoryDTO
import retrofit2.http.GET

/**
 * 网络请求接口
 *
 * @author YaDuo
 * @since 2025-06-03 11:00:18
 */
interface NetApi {

    /**
     * 获取媒体资源
     */
    @GET(RequestConstants.GET_MEDIA_SOURCE)
    suspend fun getMediaSource():MediaItemData

    /**
     * 获取精选区分类
     */
    @GET(RequestConstants.GET_SELECTED_CATEGORIES)
    suspend fun getSelectedCategories(): SelectedCategoryDTO

}