package com.example.photogallery.usergallery.presenter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.photogallery.databinding.FragmentImageDetailsScreenBinding
import com.example.photogallery.utils.loadImage

class ImageDetailsScreen : Fragment() {

    private var binding: FragmentImageDetailsScreenBinding? = null

    private val args: ImageDetailsScreenArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentImageDetailsScreenBinding.inflate(inflater)
        val imageDetails = args.imageDetails
        binding!!.detailsScreenImage.loadImage(imageDetails.url_m)
        binding!!.dateTaken.text = imageDetails.datetaken
        binding!!.title.text = imageDetails.title
        binding!!.description.text = imageDetails.description._content

        return binding!!.root
    }
}