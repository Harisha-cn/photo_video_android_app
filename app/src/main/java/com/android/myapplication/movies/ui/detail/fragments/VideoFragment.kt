package com.android.myapplication.movies.ui.detail.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.myapplication.movies.databinding.FragmentVideoBinding
import com.android.myapplication.movies.ui.detail.VideoPlayer
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class VideoFragment : Fragment() {

    private lateinit var binding: FragmentVideoBinding
    private val viewModel: VideoFragmentViewModel by sharedViewModel()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentVideoBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        initRecyclerView()
        viewModel.getMovieDetails()
        viewModel.videoDetails.observe(this, Observer {
            startActivity(VideoPlayer.getIntent(it.video_files!!.get(0).link!!, context!!))
        })
        binding.viewModel = viewModel
        return binding.root
    }

    private fun initRecyclerView() {
        val recyclerView = binding.listCasts
        recyclerView.apply {
            adapter = VideoAdapter()
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        }
    }
}