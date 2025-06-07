package com.yaduo.yaduoplayer.request

/**
 * 网络请求所需常量
 *
 * @author YaDuo
 * @since 2025-06-03 11:01:55
 */
object RequestConstants {

    /** 音加加要求获取媒体资源需携带的appid **/
    const val APPID = "3faeec81030444e98acf6af9ba32752a"

    /** 音加加要求获取媒体资源需携带的服务码 **/
    const val SERVER_CODE = "59b1aff189b3474398"

    /** 获取媒体资源，媒体来源：音加加 **/
    const val GET_MEDIA_SOURCE = "gateway-open.haifanwu.com"

    /** 获取精选区分类 **/
    const val GET_SELECTED_CATEGORIES = "api/v3/tag/list?pid=0&apiver=2&plat=0"
}