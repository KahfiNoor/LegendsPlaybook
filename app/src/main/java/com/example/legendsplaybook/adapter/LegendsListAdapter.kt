package com.example.legendsplaybook.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.legendsplaybook.R
import com.example.legendsplaybook.datas.Legends

class LegendsListAdapter(private val listLegends: ArrayList<Legends>) : RecyclerView.Adapter<LegendsListAdapter.ListViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivCoverPhoto: ImageView = itemView.findViewById(R.id.iv_item_photo)
        val tvName: TextView = itemView.findViewById(R.id.tv_item_name)
        val tvTitle: TextView = itemView.findViewById(R.id.tv_item_title)
        val tvClass: TextView = itemView.findViewById(R.id.tv_item_class)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.legends_items, parent, false)
        return ListViewHolder(view)
    }

    override fun getItemCount(): Int = listLegends.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (name, role, class_legends, photo_cover) = listLegends[position]
        holder.tvName.text = name
        holder.tvTitle.text = role
        holder.tvClass.text = class_legends
        holder.ivCoverPhoto.setImageResource(photo_cover)
        holder.itemView.setOnClickListener { onItemClickCallback.onItemClicked(listLegends[holder.adapterPosition]) }
    }

    interface OnItemClickCallback {
        fun onItemClicked(legend: Legends)
    }
}