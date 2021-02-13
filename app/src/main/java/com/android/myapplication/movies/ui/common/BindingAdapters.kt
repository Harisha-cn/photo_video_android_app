package com.android.myapplication.movies.ui.common

import YOUTUBE_THUMBNAIL_BASE_URL
import YOUTUBE_THUMBNAIL_URL_JPG
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.android.myapplication.movies.R
import com.android.myapplication.movies.listenrs.PhotoItemListener
import com.android.myapplication.movies.listenrs.VideoItemListener
import com.android.myapplication.movies.models.PhotDetails
import com.android.myapplication.movies.models.VideoDetails
import com.android.myapplication.movies.ui.detail.fragments.CastAdapter
import com.android.myapplication.movies.ui.detail.fragments.VideoAdapter
import com.android.myapplication.movies.util.Resource
import com.android.myapplication.popularmovies.api.model.*
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

@BindingAdapter("frontImage")
fun setFrontImage(imageView: ImageView, imageUrl: String?) {
    val image = imageUrl
    Glide.with(imageView.context)
        .load(image)
        .apply(
            RequestOptions()
                .error(R.drawable.ic_broken_image)
        ).into(imageView)

}

@BindingAdapter("backgroundImage")
fun setBackgroundImage(imageView: ImageView, imageUrl: String?) {
    val image = "https://images.pexels.com/photos/2877854/pexels-photo-2877854.jpeg"
    Glide.with(imageView.context)
        .load(image)
        .apply(
            RequestOptions()
                .error(R.drawable.ic_broken_image)
        ).into(imageView)

}

@BindingAdapter("videoImage")
fun setVideoImage(imageView: ImageView, videoKey: String?) {
    val image = YOUTUBE_THUMBNAIL_BASE_URL + videoKey +
            YOUTUBE_THUMBNAIL_URL_JPG
    Glide.with(imageView.context)
        .load(image)
        .apply(
            RequestOptions()
                .error(R.drawable.ic_broken_image)
        ).into(imageView)

}

@BindingAdapter("voteAvg")
fun setVoteAvg(textView: TextView, voteAvg: Double?) {
    val voteAvgString = voteAvg.toString()
    val text = voteAvgString + "/10"
    textView.setText(text)
}


@BindingAdapter("castAdapterList", "viewListeners")
fun RecyclerView.submitCastList(repoResult: Resource<PhotDetails?>?, listener: PhotoItemListener) {
    val adapter = this.adapter as CastAdapter
    adapter.listeners = listener
    repoResult?.let {
        repoResult.data?.let {
            val reviews = it.photoVideo
            reviews?.let {
                adapter.submitList(it)
            }
        }
    }
}

@BindingAdapter("castAdapterListVideo", "viewListeners")
fun RecyclerView.submitCastListVideo(
    repoResult: Resource<VideoDetails?>?,
    listener: VideoItemListener
) {
    val adapter = this.adapter as VideoAdapter
    adapter.listeners = listener
    repoResult?.let {
        repoResult.data?.let {
            val reviews = it.video
            reviews?.let {
                adapter.submitList(it)
            }
        }
    }
}

@BindingAdapter("emptyCastVisibility")
fun View.emptyCastVisibility(repoResult: Resource<PhotDetails?>?) {
    repoResult?.let {
        repoResult.data?.let {
            /* if (repoResult is Resource.Success && repoResult.data.casts.isNullOrEmpty()) {
                 this.visibility = View.VISIBLE
             } else {
                 this.visibility = View.GONE
             }*/
        }
    }

}

@BindingAdapter("emptyTrailersVisibility")
fun View.emptyTrailersVisibility(repoResult: Resource<PhotDetails?>?) {
    repoResult?.let {
        repoResult.data?.let {
            /* if (repoResult is Resource.Success && repoResult.data.trailers.isNullOrEmpty()) {
                 this.visibility = View.VISIBLE
             } else {
                 this.visibility = View.GONE
             }*/
        }
    }

}

@BindingAdapter("emptyReviewsVisibility")
fun View.emptyReviewsVisibility(repoResult: Resource<PhotDetails?>?) {
    repoResult?.let {
        repoResult.data?.let {
            /* if (repoResult is Resource.Success && repoResult.data.reviews.isNullOrEmpty()) {
                 this.visibility = View.VISIBLE
             } else {
                 this.visibility = View.GONE
             }*/
        }
    }

}

@BindingAdapter("detailNetworkErrorVisibility")
fun View.detailNetworkErrorVisibility(repoResult: Resource<PhotDetails?>?) {
    repoResult?.let {
        if (repoResult is Resource.Error) {
            this.visibility = View.VISIBLE
        } else {
            this.visibility = View.GONE
        }

    }
}

@BindingAdapter("detailProgressBarVisibility")
fun ProgressBar.detailProgressBarVisibility(repoResult: Resource<PhotDetails?>?) {
    repoResult?.let {
        if (repoResult is Resource.Loading) {
            this.visibility = View.VISIBLE
        } else {
            this.visibility = View.GONE
        }

    }
}

@BindingAdapter("detailProgressBarVisibilityVideo")
fun ProgressBar.detailProgressBarVisibilityVideo(repoResult: Resource<VideoDetails?>?) {
    repoResult?.let {
        if (repoResult is Resource.Loading) {
            this.visibility = View.VISIBLE
        } else {
            this.visibility = View.GONE
        }

    }
}