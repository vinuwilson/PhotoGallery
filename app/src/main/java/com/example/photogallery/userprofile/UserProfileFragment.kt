package com.example.photogallery.userprofile

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
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class UserProfileFragment : Fragment() {

    private lateinit var viewModel: UserProfileViewModel

    private lateinit var viewModelFactory: UserProfileViewModelFactory

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://www.flickr.com/services/rest/")
        .client(OkHttpClient())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    private val api = retrofit.create(UserProfileAPI::class.java)
    private val service = UserProfileService(api)
    private val repository = UserProfileRepository(service)

    private var binding: FragmentUserProfileBinding? = null

    private val args: UserProfileFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentUserProfileBinding.inflate(inflater)

        val userId = args.userId

        setupViewModel(userId)
        setupObserver()

        return binding!!.root
    }

    private fun setupViewModel(userId: String) {
        viewModelFactory = UserProfileViewModelFactory(repository, userId)
        viewModel = ViewModelProvider(this, viewModelFactory)[UserProfileViewModel::class.java]
    }

    private fun setupObserver() {
        viewModel.userProfile.observe(this as LifecycleOwner) { userProfile ->
            if(userProfile.getOrNull() != null)
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
            layoutManager = StaggeredGridLayoutManager(2,  StaggeredGridLayoutManager.VERTICAL)
            adapter = UserProfileAdapter(userProfile.photos.photo)
        }
    }
}