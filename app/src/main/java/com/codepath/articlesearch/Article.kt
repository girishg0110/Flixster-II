package com.codepath.articlesearch

import android.support.annotation.Keep
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class BaseResponse(
    @SerialName("results")
    val results: List<Article>?
)

@Keep
@Serializable
data class Article(
    @SerializedName("name")
    val name: String,
    @SerializedName("gender")
    val gender: String,
    @SerializedName("adult")
    val adult: Boolean,
    @SerializedName("profile_path")
    val profile_path: String? = null,
    @SerializedName("known_for_department")
    val known_for_department: String,
) : java.io.Serializable

