package com.example.userprofile.presenter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.userprofile.data.model.Photo
import com.example.userprofile.databinding.UserImageGridBinding
import com.example.userprofile.loadImage

class UserProfileAdapter(
    private val photo: List<Photo>
) : RecyclerView.Adapter<UserProfileAdapter.ViewHolder>() {

    inner class ViewHolder(binding: UserImageGridBinding) : RecyclerView.ViewHolder(binding.root) {
        val image :ImageView = binding.galleryImage
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            UserImageGridBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = photo[position]
        holder.image.loadImage(item.image)
    }

    override fun getItemCount(): Int =  photo.size

}
