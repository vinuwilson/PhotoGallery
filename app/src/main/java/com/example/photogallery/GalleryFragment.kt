package com.example.photogallery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.photogallery.databinding.FragmentGalleryBinding
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class GalleryFragment : Fragment() {

    private lateinit var viewModel: GalleryViewModel
    private lateinit var viewModelFactory: GalleryViewModelFactory

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://www.flickr.com/services/rest/")
        .client(OkHttpClient())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    private val api = retrofit.create(GalleryAPI::class.java)
    private val service = GalleryServices(api)
    private val repository = GalleryRepository(service)
    private var binding: FragmentGalleryBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentGalleryBinding.inflate(inflater)

        setupViewModel()
        setupObserver()
        return binding!!.root
    }

    private fun setupObserver() {
        viewModel.galleryList.observe(this) { galleryList ->
            if (galleryList.getOrNull() != null)
                setupListView(binding!!.galleryList, galleryList.getOrNull()!!)
        }
    }

    private fun setupListView(galleryList: RecyclerView, photosResponse: PhotosRecentResponse) {
        with(galleryList) {
            layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
            adapter = GalleryAdapter(photosResponse.photos.photo) { userDetails ->
                if (userDetails.second) {
                    val action =
                        GalleryFragmentDirections.actionGalleryFragmentToImageDetailsScreen(userDetails.first)
                    findNavController().navigate(action)
                } else {
                    val userProfile =
                        GalleryFragmentDirections.actionGalleryFragmentToUserProfileFragment(userDetails.first.owner)
                    findNavController().navigate(userProfile)
                }
            }
        }
    }

    private fun setupViewModel() {
        viewModelFactory = GalleryViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory)[GalleryViewModel::class.java]
    }
}