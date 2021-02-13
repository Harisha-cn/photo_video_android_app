package com.android.myapplication.movies.api

import androidx.lifecycle.LiveData
import com.android.myapplication.movies.api.responses.ApiResponse
import com.android.myapplication.movies.models.PhotsVideoResponse
import com.android.myapplication.movies.models.VideoResponse

import retrofit2.http.*

interface MoviesApi {
    @Headers("Authorization:563492ad6f91700001000001c09048aa6dbb44029a529b85e2d678de")
    @GET("search")
    fun getPhoto(
        @Query("query") query: String = "animal"
    ): LiveData<ApiResponse<PhotsVideoResponse>>

    @Headers("Authorization:563492ad6f91700001000001c09048aa6dbb44029a529b85e2d678de")
    @GET("videos/search")
    fun getVideo(
        @Query("query") query: String = "animal"
    ): LiveData<ApiResponse<VideoResponse>>

}