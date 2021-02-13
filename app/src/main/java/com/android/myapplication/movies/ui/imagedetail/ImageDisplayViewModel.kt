package com.android.myapplication.movies.ui.imagedetail

import android.app.Application
import androidx.databinding.ObservableField
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.android.myapplication.movies.listenrs.PhotoItemListener
import com.android.myapplication.movies.listenrs.VideoItemListener
import com.android.myapplication.movies.models.PhotDetails
import com.android.myapplication.movies.models.Photo
import com.android.myapplication.movies.models.Video
import com.android.myapplication.movies.repository.MovieDetailRepository
import com.android.myapplication.movies.util.Resource

class ImageDisplayViewModel(app: Application, private val repository: MovieDetailRepository) :
    AndroidViewModel(app) {


    private val _movieDetails = MediatorLiveData<Resource<PhotDetails>>()
    private val _photoDetails = MediatorLiveData<Photo>()

    var imageType = ObservableField<String>("")


    val movieDetails: LiveData<Resource<PhotDetails>>
        get() = _movieDetails

    val photoDetails: LiveData<Photo>
        get() = _photoDetails
    private var cancelRequest = false
    private var isPerformingQuery: Boolean = false

    fun updateImage(image: String) {
        imageType.set(image)
    }

}