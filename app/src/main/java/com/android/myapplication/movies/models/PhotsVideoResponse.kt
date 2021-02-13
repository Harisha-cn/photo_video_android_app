package com.android.myapplication.movies.models

import android.os.Parcelable
import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


@Parcelize
data class PhotsVideoResponse(

    @SerializedName("page")
    @Expose
     val page: Int? = null,

    @SerializedName("per_page")
    @Expose
     val perPage: Int? = null,

    @SerializedName("photos")
    @Expose
     val photos: List<Photo>? = null,

    @SerializedName("total_results")
    @Expose
     val totalResults: Int? = null,

    @SerializedName("next_page")
    @Expose
     val nextPage: String? = null

) : Parcelable

@Parcelize
data class Photo(
    @SerializedName("id") val id: Int? = null,
    @SerializedName("width")
     val width: Int? = null,
    @SerializedName("height")
     val height: Int? = null,
    @SerializedName("url")
     val url: String? = null,
    @SerializedName("src")
    val src: SRC? = null,
    @SerializedName("photographer")
     val photographer: String? = null,
    @SerializedName("photographer_url")
     val photographer_url: String? = null,
    @SerializedName("photographer_id")
     val photographer_id: Int? = null,
    @SerializedName("avg_color")
     val avg_color: String? = null,

    @SerializedName("liked")
    var liked: Boolean
) : Parcelable

@Parcelize
data class SRC(
    @SerializedName("original")
    val original: String? = null,
    @SerializedName("medium")
    val medium: String? = null


) : Parcelable