package com.android.myapplication.movies.ui.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.viewpager2.widget.ViewPager2
import com.android.myapplication.movies.R
import com.android.myapplication.movies.databinding.ActivityDetailBinding
import com.android.myapplication.movies.databinding.ActivityVideoPlayerBinding
import com.android.myapplication.movies.ui.detail.fragments.DetailFragmentViewModel
import com.android.myapplication.popularmovies.api.model.Video
import com.google.android.material.tabs.TabLayout
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class VideoPlayer : AppCompatActivity() {
    private lateinit var binding: ActivityVideoPlayerBinding
    private lateinit var viewPager: ViewPager2
    private lateinit var tabLayout: TabLayout
    private val viewModel: DetailFragmentViewModel by viewModel {

        parametersOf(
            intent?.extras?.getParcelable(INTENT_EXTRA_MOVIE) ?: Video()
        )
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_video_player)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        binding.andExoPlayerView.setSource(intent?.extras?.getString(INTENT_EXTRA_MOVIE))
        setSupportActionBar(findViewById(R.id.toolbar))

    }
    companion object {
        fun getIntent( movie:String,context: Context): Intent {
            val intent = Intent(context, VideoPlayer::class.java)
            intent.putExtra(INTENT_EXTRA_MOVIE, movie)
            return intent
        }
    }
}