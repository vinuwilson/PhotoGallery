package com.example.photogallery.userprofile.presenter

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
import com.example.photogallery.databinding.FragmentUserProfileBinding
import com.example.photogallery.userprofile.data.model.UserProfile
import com.example.photogallery.utils.loadImage
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

        binding!!.coverPhoto.loadImage(args.userDetails.url_m)
        binding!!.avatar.loadImage(args.userDetails.avatar)
        binding!!.title.text = args.userDetails.ownername

        return binding!!.root
    }

    private fun setupObserver() {
        viewModel.getUserProfile.observe(this as LifecycleOwner) { userProfile ->
            if (userProfile.getOrNull() != null)
                setupListView(binding!!.userImageGrid, userProfile.getOrNull()!!)
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
}