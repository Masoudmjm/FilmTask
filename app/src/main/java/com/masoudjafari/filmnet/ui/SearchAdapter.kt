package com.masoudjafari.filmnet.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.masoudjafari.filmnet.R
import com.masoudjafari.filmnet.data.model.DataItem
import com.masoudjafari.filmnet.databinding.ItemSearchBinding

class SearchAdapter : RecyclerView.Adapter<SearchAdapter.ViewHolder>() {
    private var data = mutableListOf<DataItem>()
    private lateinit var callback: SearchAdapterCallback

    fun setData(data: List<DataItem>, newPhrase: Boolean) {
        if (newPhrase) {
            this.data = data.toMutableList()
            notifyDataSetChanged()
        }
        else {
            val lastSize = this.data.size
            this.data.addAll(data.toMutableList())
            notifyItemRangeInserted(lastSize, data.size)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemSearchBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        if (position == data.size-1)
            callback.loadMoreItems(position)

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

    interface SearchAdapterCallback {
        fun loadMoreItems(position: Int)
    }

    fun setCallback(callback: SearchAdapterCallback) {
        this.callback = callback
    }

    class ViewHolder(val binding: ItemSearchBinding) : RecyclerView.ViewHolder(binding.root)
}


