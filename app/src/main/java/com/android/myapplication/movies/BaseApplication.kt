package com.android.myapplication.movies

import BASE_URL
import android.app.Application
import com.android.myapplication.movies.api.MoviesApi
import com.android.myapplication.movies.repository.MovieDetailRepository
import com.android.myapplication.movies.ui.detail.fragments.DetailFragmentViewModel
import com.android.myapplication.movies.ui.detail.fragments.VideoFragmentViewModel
import com.android.myapplication.movies.ui.detail.fragments.VideoPlayerViewModel
import com.android.myapplication.movies.ui.imagedetail.ImageDisplayViewModel
import com.android.myapplication.movies.util.AppExecutors
import com.android.myapplication.popularmovies.api.model.Video
import com.android.myapplication.popularmovies.util.LiveDataCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class BaseApplication : Application() {
    private val appModule = module {
        single<MoviesApi> {
            val logger = HttpLoggingInterceptor()
            logger.setLevel(HttpLoggingInterceptor.Level.BASIC)
            val client = OkHttpClient.Builder()
                .addInterceptor(logger)
                .connectTimeout(60L, TimeUnit.SECONDS)
                .readTimeout(60L, TimeUnit.SECONDS)
                .retryOnConnectionFailure(false)
                .build()
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(LiveDataCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build().create(MoviesApi::class.java)
        }

        single<MovieDetailRepository> {
            val moviesApi: MoviesApi = get()
            MovieDetailRepository(moviesApi)
        }

        single<AppExecutors> {
            AppExecutors()
        }

        viewModel<DetailFragmentViewModel> {
            val repository: MovieDetailRepository = get()
            DetailFragmentViewModel(
                this@BaseApplication,
                repository
            )
        }
        viewModel<VideoFragmentViewModel> {
            val repository: MovieDetailRepository = get()
            VideoFragmentViewModel(
                this@BaseApplication,
                repository
            )
        }
        viewModel<VideoPlayerViewModel> { (movie: Video) ->
            val repository: MovieDetailRepository = get()
            VideoPlayerViewModel(
                this@BaseApplication,
                repository
            )
        }
        viewModel<ImageDisplayViewModel> {
            val repository: MovieDetailRepository = get()
            ImageDisplayViewModel(
                this@BaseApplication,
                repository
            )
        }
    }

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@BaseApplication)
            modules(appModule)
        }
    }
}