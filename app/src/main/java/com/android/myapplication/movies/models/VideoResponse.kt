package com.android.myapplication.movies.models

import android.os.Parcelable
import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


@Parcelize
data class VideoResponse(

    @SerializedName("page")
    @Expose
    val page: Int? = null,

    @SerializedName("per_page")
    @Expose
    val perPage: Int? = null,

    @SerializedName("videos")
    @Expose
    val videos: List<Video>? = null,

    @SerializedName("total_results")
    @Expose
    val totalResults: Int? = null



) : Parcelable

@Parcelize
data class Video(
    @SerializedName("id") val id: Int? = null,
    @SerializedName("width")
    val width: Int? = null,
    @SerializedName("height")
    val height: Int? = null,
    @SerializedName("url")
    val url: String? = null,
    @SerializedName("image")
    val image: String? = null,
    @SerializedName("video_files")
    val video_files: List<VideoFiles>? = null,
    @SerializedName("user")
    val user: User? = null

) : Parcelable

@Parcelize
data class User(

    @SerializedName("id") val id: Int? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("url")
    val url: String? = null


) : Parcelable

@Parcelize
data class VideoFiles(

    @SerializedName("id") val id: Int? = null,
    @SerializedName("quality")
    val quality: String? = null,
    @SerializedName("file_type")
    val file_type: String? = null,
    @SerializedName("link")
    val link: String? = null


) : Parcelable