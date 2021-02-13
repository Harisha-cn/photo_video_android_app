package com.android.myapplication.popularmovies.api.responses


import com.android.myapplication.popularmovies.api.responses.inner.VideoResponse
import com.google.gson.annotations.SerializedName

//response for specific movie detail request
data class MovieDetailsResponse(
    @SerializedName("videos")
    val videoResponse: VideoResponse? = null


)