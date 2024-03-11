package com.example.userprofile.presenter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.userprofile.data.model.Photos
import com.example.userprofile.data.model.UserProfile
import com.example.userprofile.databinding.FragmentUserProfileBinding
import com.example.userprofile.loadImage
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserProfileFragment : Fragment() {

    private val viewModel: UserProfileViewModel by viewModels()

    private var binding: FragmentUserProfileBinding? = null

    private val args: UserProfileFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentUserProfileBinding.inflate(inflater)

        setupObserver()

        return binding!!.root
    }

    private fun setupObserver() {
        viewModel.getUserProfile.observe(this as LifecycleOwner) { userProfile ->
            if (userProfile.getOrNull() != null) {
                setupListView(binding!!.userImageGrid, userProfile.getOrNull()!!)
                createUI(userProfile.getOrNull()!!.photos)
            }
        }

        viewModel.loader.observe(this as LifecycleOwner) { loading ->
            when (loading) {
                true -> binding!!.progressBar.visibility = View.VISIBLE
                else -> binding!!.progressBar.visibility = View.GONE
            }
        }
    }

    private fun setupListView(userImageGrid: RecyclerView, userProfile: UserProfile) {
        with(userImageGrid) {
            layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            adapter = UserProfileAdapter(userProfile.photos.photo)
        }
    }

    private fun createUI(photos: Photos) {
        binding!!.coverPhoto.loadImage(photos.photo[0].image)
        binding!!.avatar.loadImage(photos.photo[0].image)
        binding!!.title.text = args.userDetails
    }
}