package com.example.photogallery.userprofile.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.photogallery.databinding.FragmentUserProfileBinding
import com.example.photogallery.userprofile.model.UserProfile
import com.example.photogallery.userprofile.viewmodel.UserProfileViewModel
import com.example.photogallery.userprofile.viewmodel.UserProfileViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class UserProfileFragment : Fragment() {

    private lateinit var viewModel: UserProfileViewModel

    @Inject
    lateinit var viewModelFactory: UserProfileViewModelFactory

    private var binding: FragmentUserProfileBinding? = null

    private val args: UserProfileFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentUserProfileBinding.inflate(inflater)

        setupViewModel()
        setupObserver()

        viewModel.getUserProfile(args.userId)
        return binding!!.root
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(this, viewModelFactory)[UserProfileViewModel::class.java]
    }

    private fun setupObserver() {
        viewModel.userProfile.observe(this as LifecycleOwner) { userProfile ->
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
        with(userImageGrid as RecyclerView) {
            layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            adapter = UserProfileAdapter(userProfile.photos.photo)
        }
    }
}