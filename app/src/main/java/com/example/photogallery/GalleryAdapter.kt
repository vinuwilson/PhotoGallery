package com.example.photogallery

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.photogallery.databinding.GalleryItemBinding

class GalleryAdapter(
    private val values: List<Photo>
) : RecyclerView.Adapter<GalleryAdapter.ViewHolder>() {

    inner class ViewHolder(binding: GalleryItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val galleryImage: ImageView = binding.galleryImage
        val avatar: ImageView = binding.avatar
        val ownerId: TextView = binding.ownerId
        val ownerName: TextView = binding.ownerName
        val tags: TextView = binding.tags
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            GalleryItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.galleryImage.loadImage(item.url_s)
        holder.avatar.loadImage(item.url_s)
        holder.ownerId.text = item.owner
        holder.ownerName.text = item.ownername
        holder.tags.text = item.tags
    }

    override fun getItemCount(): Int = values.size
}