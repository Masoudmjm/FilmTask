package com.masoudjafari.filmnet.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.masoudjafari.filmnet.R
import com.masoudjafari.filmnet.data.model.SearchResponse
import com.masoudjafari.filmnet.data.model.VideosItem
import com.masoudjafari.filmnet.databinding.ItemSearchBinding

class SearchAdapter : RecyclerView.Adapter<SearchAdapter.ViewHolder>() {
    private var data = mutableListOf<VideosItem>()

    fun setData(data: List<VideosItem>) {
        this.data = data.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemSearchBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val item = data[position]
        holder.binding.tvTitle.text = item.title

        Glide.with(holder.itemView.context)
            .load(item.coverImage?.path)
            .error(R.drawable.ic_launcher_foreground)
            .into(holder.binding.image)

    }

    override fun getItemCount(): Int {
        return data.size
    }

    class ViewHolder(val binding: ItemSearchBinding) : RecyclerView.ViewHolder(binding.root)
}


