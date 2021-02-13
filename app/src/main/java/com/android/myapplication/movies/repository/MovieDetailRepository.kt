package com.android.myapplication.movies.repository

import androidx.lifecycle.LiveData
import com.android.myapplication.movies.api.MoviesApi
import com.android.myapplication.movies.api.responses.ApiEmptyResponse
import com.android.myapplication.movies.api.responses.ApiErrorResponse
import com.android.myapplication.movies.api.responses.ApiResponse
import com.android.myapplication.movies.api.responses.ApiSuccessResponse
import com.android.myapplication.movies.models.*
import com.android.myapplication.movies.util.NetworkBoundResourceNoCaching
import com.android.myapplication.movies.util.Resource

class MovieDetailRepository(
    private val movieApi: MoviesApi
) {

    fun getMovieDetail(): LiveData<Resource<PhotDetails>> {
        return object : NetworkBoundResourceNoCaching<PhotDetails, PhotsVideoResponse>() {
            override fun handleApiSuccessResponse(response: ApiSuccessResponse<PhotsVideoResponse>) {
                val detailsResponse = response.body

                result.value = Resource.Success(PhotDetails(detailsResponse.photos))
            }

            override fun handleApiEmptyResponse(response: ApiEmptyResponse<PhotsVideoResponse>) {
                //MovieDetails,has empty videos,casts,reviews
                result.value = Resource.Success(PhotDetails())
            }

            override fun handleApiErrorResponse(response: ApiErrorResponse<PhotsVideoResponse>) {
                result.value = Resource.Error(response.errorMessage, null)
            }

            override fun createCall(): LiveData<ApiResponse<PhotsVideoResponse>> =
                movieApi.getPhoto("animal")

        }.asLiveData()
    }

    fun getVideoDetail(): LiveData<Resource<VideoDetails>> {
        return object : NetworkBoundResourceNoCaching<VideoDetails, VideoResponse>() {
            override fun handleApiSuccessResponse(response: ApiSuccessResponse<VideoResponse>) {
                val detailsResponse = response.body

                result.value = Resource.Success(VideoDetails(detailsResponse.videos))
            }

            override fun handleApiEmptyResponse(response: ApiEmptyResponse<VideoResponse>) {
                //MovieDetails,has empty videos,casts,reviews
                result.value = Resource.Success(VideoDetails())
            }

            override fun handleApiErrorResponse(response: ApiErrorResponse<VideoResponse>) {
                result.value = Resource.Error(response.errorMessage, null)
            }

            override fun createCall(): LiveData<ApiResponse<VideoResponse>> =
                movieApi.getVideo("animal")

        }.asLiveData()
    }
}