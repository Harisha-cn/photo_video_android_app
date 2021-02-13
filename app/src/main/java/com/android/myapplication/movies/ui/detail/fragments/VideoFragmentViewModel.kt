package com.android.myapplication.movies.ui.detail.fragments

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.android.myapplication.movies.listenrs.VideoItemListener
import com.android.myapplication.movies.models.Video
import com.android.myapplication.movies.models.VideoDetails
import com.android.myapplication.movies.repository.MovieDetailRepository
import com.android.myapplication.movies.util.Resource

class VideoFragmentViewModel(app: Application, private val repository: MovieDetailRepository) :
    AndroidViewModel(app) {


    private val _movieDetails = MediatorLiveData<Resource<VideoDetails>>()
    private val _videoDetails = MediatorLiveData<Video>()
    val movieDetails: LiveData<Resource<VideoDetails>>
        get() = _movieDetails

    val videoDetails: LiveData<Video>
        get() = _videoDetails

    private var cancelRequest = false
    private var isPerformingQuery: Boolean = false

    fun getMovieDetails() {
        if (!isPerformingQuery) {
            isPerformingQuery = true
            executeRequest()
        }
    }

    private fun executeRequest() {
        val repositorySource = repository.getVideoDetail()
        registerMediatorLiveData(repositorySource)
    }

    var videoItemListener = object : VideoItemListener {
        override fun onItemClick(item: Video) {
            _videoDetails.value = item

        }

    }

    fun registerMediatorLiveData(repositorySource: LiveData<Resource<VideoDetails>>) {
        _movieDetails.addSource(repositorySource) { resourceMovieDetails ->
            if (!cancelRequest) {
                if (resourceMovieDetails != null) {
                    _movieDetails.value = resourceMovieDetails
                    if (resourceMovieDetails is Resource.Success || resourceMovieDetails is Resource.Error) {
                        //if response is reached error or success (no more loading)
                        unregisterMediatorLiveData(repositorySource)
                    }
                } else {
                    //if response is null
                    unregisterMediatorLiveData(repositorySource)
                }
            } else {
                //if request is canceled
                unregisterMediatorLiveData(repositorySource)
            }
        }
    }

    //unregister when whole response is null or when response ==Success or Error
    private fun unregisterMediatorLiveData(repositorySource: LiveData<Resource<VideoDetails>>) {
        isPerformingQuery = false
        _movieDetails.removeSource(repositorySource)
    }


    fun cancelRequest() {
        cancelRequest = true
    }
}