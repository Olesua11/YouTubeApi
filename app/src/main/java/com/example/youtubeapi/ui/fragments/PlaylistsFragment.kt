package com.example.youtubeapi.ui.fragments

import androidx.core.view.isVisible
import androidx.lifecycle.viewModelScope
import com.example.youtubeapi.databinding.FragmentPlaylistsBinding
import com.example.youtubeapi.ui.PlaylistsAdapter
import com.example.youtubeapi.ui.BaseFragment
import com.example.youtubeapi.ui.viewmodels.PlaylistsViewModel
import com.example.youtubeapi.ui.Resource
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class PlaylistsFragment : BaseFragment<FragmentPlaylistsBinding, PlaylistsViewModel>(FragmentPlaylistsBinding::inflate) {

    override val viewModel: PlaylistsViewModel by viewModel()
    private val adapter: PlaylistsAdapter by inject()

    override fun init() {
        super.init()
        binding.rvPlaylists.adapter = adapter
        viewModel.fetchPlaylists()
    }

    override fun observe() {
        super.observe()
        viewModel.playlists.observe(viewLifecycleOwner) { resource ->
            when (resource) {
                is Resource.Loading -> binding.animLoading.isVisible = true
                is Resource.Success -> {
                    binding.animLoading.isVisible = false
                    resource.data?.let { playlists ->
                        adapter.submitList(playlists.items)
                    }
                }
                is Resource.Error -> {
                    binding.animLoading.isVisible = false
                    // Handle error
                }
            }
        }
    }
}
