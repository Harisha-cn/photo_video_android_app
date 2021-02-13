package com.android.myapplication.movies.ui.imagedetail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.animation.AnimationUtils
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.viewpager2.widget.ViewPager2
import com.android.myapplication.movies.R
import com.android.myapplication.movies.databinding.ActivityDetailBinding
import com.android.myapplication.movies.databinding.ActivityImageBinding
import com.android.myapplication.movies.databinding.ActivityVideoPlayerBinding
import com.android.myapplication.movies.ui.detail.INTENT_EXTRA_MOVIE
import com.android.myapplication.movies.ui.detail.fragments.DetailFragmentViewModel
import com.android.myapplication.popularmovies.api.model.Video
import com.google.android.material.tabs.TabLayout
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class ImageDisplay : AppCompatActivity() {
    private lateinit var binding: ActivityImageBinding
    private lateinit var viewPager: ViewPager2
    private lateinit var tabLayout: TabLayout
    private val viewModel: ImageDisplayViewModel by viewModel {

        parametersOf(
            intent?.extras?.getParcelable(INTENT_EXTRA_MOVIE) ?: Video()
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_image)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        viewModel.updateImage(intent?.extras?.getString(INTENT_EXTRA_MOVIE)!!)

        // actions to be performed when
        // "Zoom In" button is clicked
        binding.zoomInButton.setOnClickListener() {

            // loading the animation of
            // zoom_in.xml file into a variable
            val animZoomIn = AnimationUtils.loadAnimation(
                this,
                R.animator.zoomin
            )
            // assigning that animation to
            // the image and start animation
            binding.imageView.startAnimation(animZoomIn)
        }

        // actions to be performed when
        // "Zoom Out" button is clicked
        binding.zoomOutButton.setOnClickListener() {

            // loading the animation of
            // zoom_out.xml file into a variable
            val animZoomOut = AnimationUtils.loadAnimation(
                this,
                R.animator.zoomout
            )

            // assigning that animation to
            // the image and start animation
            binding.imageView.startAnimation(animZoomOut)
        }
        setSupportActionBar(findViewById(R.id.toolbar))

    }

    companion object {
        fun getIntent(movie: String, context: Context): Intent {
            val intent = Intent(context, ImageDisplay::class.java)
            intent.putExtra(INTENT_EXTRA_MOVIE, movie)
            return intent
        }
    }
}