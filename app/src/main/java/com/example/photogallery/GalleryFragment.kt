package com.example.photogallery

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
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
    private lateinit var customAdapter : GalleryAdapter
    private lateinit var photoRes :PhotosRecentResponse
    private var gridFlag: Boolean = true
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentGalleryBinding.inflate(inflater)

        setupViewModel()
        setupObserver()
        filterGallery()

        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.main_menu, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when (menuItem.itemId) {
                    R.id.search -> {
                        binding!!.search.visibility = View.VISIBLE
                        true
                    }
                    R.id.grid -> {
                        if(gridFlag) {
                            setupListView(2, binding!!.galleryList, photoRes)
                            menuItem.title = "List"
                            menuItem.icon = ContextCompat.getDrawable(requireContext(), R.drawable.ic_action_list)
                            gridFlag = false
                        } else {
                            setupListView(1, binding!!.galleryList, photoRes)
                            menuItem.title = "Grid"
                            menuItem.icon = ContextCompat.getDrawable(requireContext(), R.drawable.ic_action_grid)
                            gridFlag = true
                        }
                        true
                    }
                    else -> false
                }
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)

        return binding!!.root
    }

    private fun filterGallery() {
        binding!!.search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                customAdapter.filter.filter(newText)
                return false
            }
        })
    }

    private fun setupObserver() {
        viewModel.galleryList.observe(this) { galleryList ->
            if (galleryList.getOrNull() != null) {
                photoRes = galleryList.getOrNull()!!
                setupListView(1, binding!!.galleryList, galleryList.getOrNull()!!)
            }
        }

        viewModel.loader.observe(this as LifecycleOwner) { loading ->
            when (loading) {
                true -> binding!!.loader.visibility = View.VISIBLE
                else -> binding!!.loader.visibility = View.GONE
            }
        }
    }

    private fun setupListView(count: Int, galleryList: RecyclerView, photosResponse: PhotosRecentResponse) {
        with(galleryList) {
            layoutManager = StaggeredGridLayoutManager(count, StaggeredGridLayoutManager.VERTICAL)
            customAdapter = GalleryAdapter(photosResponse.photos.photo) { userDetails ->
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
            galleryList.adapter = customAdapter
            galleryList.adapter!!.stateRestorationPolicy = RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
        }
    }

    private fun setupViewModel() {
        viewModelFactory = GalleryViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory)[GalleryViewModel::class.java]
    }
}