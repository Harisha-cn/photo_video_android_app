package com.android.myapplication.movies.ui.detail.fragments

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.android.myapplication.movies.models.PhotDetails
import com.android.myapplication.movies.repository.MovieDetailRepository
import com.android.myapplication.movies.util.Resource

class VideoPlayerViewModel(app: Application, private val repository: MovieDetailRepository) : AndroidViewModel(app) {


    private val _movieDetails = MediatorLiveData<Resource<PhotDetails>>()
    val movieDetails: LiveData<Resource<PhotDetails>>
        get() = _movieDetails


    private var cancelRequest = false
    private var isPerformingQuery: Boolean = false

    fun getMovieDetails(){
        if (!isPerformingQuery) {
            isPerformingQuery = true
            executeRequest()
        }
    }
    private fun executeRequest() {
        val repositorySource = repository.getMovieDetail()
        registerMediatorLiveData(repositorySource)
    }

    fun registerMediatorLiveData(repositorySource: LiveData<Resource<PhotDetails>>) {
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
    private fun unregisterMediatorLiveData(repositorySource:  LiveData<Resource<PhotDetails>>) {
        isPerformingQuery = false
        _movieDetails.removeSource(repositorySource)
    }


    fun cancelRequest() {
        cancelRequest = true
    }
}