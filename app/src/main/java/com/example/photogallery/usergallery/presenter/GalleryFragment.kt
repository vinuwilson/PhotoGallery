package com.example.photogallery.usergallery.presenter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.photogallery.R
import com.example.photogallery.databinding.FragmentGalleryBinding
import com.example.photogallery.usergallery.data.model.RecentPhotos
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GalleryFragment : Fragment() {

    private val viewModel: GalleryViewModel by viewModels()

    private var binding: FragmentGalleryBinding? = null

    private lateinit var customAdapter : GalleryAdapter

    private lateinit var photoRes : RecentPhotos

    private var gridFlag: Boolean = true

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentGalleryBinding.inflate(inflater)

        setupObserver()
        filterGallery()
        setupMenu()

        return binding!!.root
    }

    private fun setupMenu() {
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
                            menuItem.icon = ContextCompat.getDrawable(requireContext(),
                                R.drawable.ic_action_list
                            )
                            gridFlag = false
                        } else {
                            setupListView(1, binding!!.galleryList, photoRes)
                            menuItem.title = "Grid"
                            menuItem.icon = ContextCompat.getDrawable(requireContext(),
                                R.drawable.ic_action_grid
                            )
                            gridFlag = true
                        }
                        true
                    }
                    else -> false
                }
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
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
        viewModel.galleryList.observe(viewLifecycleOwner) { galleryList ->
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

    private fun setupListView(count: Int, galleryList: RecyclerView, photosResponse: RecentPhotos) {
        with(galleryList) {
            layoutManager = StaggeredGridLayoutManager(count, StaggeredGridLayoutManager.VERTICAL)
            customAdapter = GalleryAdapter(photosResponse.photos.photo) { userDetails ->
                if (userDetails.second) {
                    val action =
                        GalleryFragmentDirections.actionGalleryFragmentToImageDetailsScreen(userDetails.first)
                    findNavController().navigate(action)
                } else {
                    val userProfile =
                        GalleryFragmentDirections.actionGalleryFragmentToUserProfileFragment(userDetails.first)
                    findNavController().navigate(userProfile)
                }
            }
            galleryList.adapter = customAdapter
            galleryList.adapter!!.stateRestorationPolicy = RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
        }
    }

}