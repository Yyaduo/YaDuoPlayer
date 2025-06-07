package com.yaduo.yaduoplayer.model

import com.google.gson.annotations.SerializedName

/**
 * 精选区分类数据类
 *
 * @property data 分类数据主体
 * @property errCode 错误码 (0 表示成功)
 * @property status 请求状态 (1 表示成功)
 * @property error 错误信息 (成功时为空)
 *
 * @author YaDuo
 * @since 2025-06-07 16:23:38
 */
data class SelectedCategoryDTO(
    @SerializedName("data") val data: CategoryData,
    @SerializedName("errcode") val errCode: Int,
    @SerializedName("status") val status: Int,
    @SerializedName("error") val error: String
)

/**
 * 分类数据主体
 *
 * @property timestamp 数据时间戳 (Unix 时间戳)
 * @property info 分类信息列表
 */
data class CategoryData(
    @SerializedName("timestamp") val timestamp: Long,
    @SerializedName("info") val info: List<CategoryItem>
)

/**
 * 分类项
 *
 * @property appids 应用ID (通常为空)
 * @property hasChild 是否有子分类 (1 有, 0 无)
 * @property id 分类ID
 * @property subtitle 子标题
 * @property icon 分类图标URL ({ size } 需要替换为实际尺寸如 200x200)
 * @property k20BlackIcon 暗黑模式下使用的黑色图标
 * @property description 分类描述
 * @property name 分类名称
 * @property k20WhiteIcon 暗黑模式下使用的白色图标
 * @property children 子分类列表
 * @property k12Icon 青少年模式下的图标
 */
data class CategoryItem(
    @SerializedName("appids") val appids: String,
    @SerializedName("has_child") val hasChild: Int,
    @SerializedName("id") val id: Int,
    @SerializedName("subtitle") val subtitle: String,
    @SerializedName("icon") val icon: String,
    @SerializedName("k20_black_icon") val k20BlackIcon: String,
    @SerializedName("description") val description: String,
    @SerializedName("name") val name: String,
    @SerializedName("k20_white_icon") val k20WhiteIcon: String,
    @SerializedName("children") val children: List<CategoryChild>,
    @SerializedName("k12_icon") val k12Icon: String
)

/**
 * 子分类项
 *
 * @property appids 应用ID (通常为空)
 * @property k20BlackIcon 暗黑模式下使用的黑色图标
 * @property id 子分类ID
 * @property jumpUrl 跳转URL (点击后打开的页面)
 * @property icon 分类图标URL
 * @property description 分类描述
 * @property bannerUrl 横幅图片URL ({ size } 需要替换为实际尺寸)
 * @property songTagId 歌曲标签ID (关联歌曲分类)
 * @property k12Icon 青少年模式下的图标
 * @property hasChild 是否有子分类
 * @property name 子分类名称
 * @property albumTagId 专辑标签ID (关联专辑分类)
 * @property specialTagId 专题标签ID (关联专题分类)
 * @property subtitle 子标题
 * @property k20WhiteIcon 暗黑模式下使用的白色图标
 * @property isHot 是否热门 (1 是, 0 否)
 * @property isNew 是否新分类 (1 是, 0 否)
 * @property bannerHd 高清横幅图片URL
 * @property theme 主题类型 (1 普通)
 * @property imgUrl 图片URL (备用图标)
 */
data class CategoryChild(
    @SerializedName("appids") val appids: String,
    @SerializedName("k20_black_icon") val k20BlackIcon: String,
    @SerializedName("id") val id: Int,
    @SerializedName("jump_url") val jumpUrl: String,
    @SerializedName("icon") val icon: String,
    @SerializedName("description") val description: String,
    @SerializedName("bannerurl") val bannerUrl: String,
    @SerializedName("song_tag_id") val songTagId: Int,
    @SerializedName("k12_icon") val k12Icon: String,
    @SerializedName("has_child") val hasChild: Int,
    @SerializedName("name") val name: String,
    @SerializedName("album_tag_id") val albumTagId: Int,
    @SerializedName("special_tag_id") val specialTagId: Int,
    @SerializedName("subtitle") val subtitle: String,
    @SerializedName("k20_white_icon") val k20WhiteIcon: String,
    @SerializedName("is_hot") val isHot: Int,
    @SerializedName("is_new") val isNew: Int,
    @SerializedName("banner_hd") val bannerHd: String,
    @SerializedName("theme") val theme: Int,
    @SerializedName("imgurl") val imgUrl: String
)