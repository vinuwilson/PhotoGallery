package com.example.photogallery.usergallery.presenter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.photogallery.databinding.GalleryItemBinding
import com.example.photogallery.utils.loadImage
import com.example.photogallery.usergallery.data.model.Photo

class GalleryAdapter(
    private val values: List<Photo>,
    private val listener: (Pair<Photo, Boolean>) -> Unit
) : RecyclerView.Adapter<GalleryAdapter.ViewHolder>(), Filterable{

    var galleryList = values

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
        val item = galleryList[position]
        holder.galleryImage.loadImage(item.url_m)
        holder.avatar.loadImage(item.avatar)
        holder.ownerId.text = item.owner
        holder.ownerName.text = item.ownername
        holder.tags.text = item.tags
        holder.galleryImage.setOnClickListener { listener(Pair(item, true)) }
        holder.avatar.setOnClickListener { listener(Pair(item, false)) }
    }

    override fun getItemCount(): Int = galleryList.size

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence): FilterResults {
                val filteredGalleryList = values.filter {
                    it.tags.contains(
                        constraint,
                        ignoreCase = true
                    ) || it.ownername.contains(constraint, ignoreCase = true)
                }
                val filterResults = FilterResults()
                filterResults.values = filteredGalleryList
                return filterResults
            }

            @SuppressLint("NotifyDataSetChanged")
            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                galleryList = results?.values as List<Photo>
                notifyDataSetChanged()
            }
        }
    }
}