package com.example.viewmodel

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.viewmodel.R

class DramaAdapter(
    private var listDrama: List<Drama>,
    private val onWikiClick: (String) -> Unit,
    private val onDetailClick: (Drama) -> Unit
) : RecyclerView.Adapter<DramaAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgPhoto: ImageView = itemView.findViewById(R.id.img_item_photo)
        val tvTitle: TextView = itemView.findViewById(R.id.tv_item_name)
        val tvPlot: TextView = itemView.findViewById(R.id.tv_item_plot)
        val btnWiki: Button = itemView.findViewById(R.id.btn_wiki)
        val btnDetail: Button = itemView.findViewById(R.id.button_detail)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = listDrama.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val drama = listDrama[position]

        android.util.Log.d("DramaAdapter", "Data masuk ke list: ${drama.title}")

        holder.tvTitle.text = drama.title
        holder.tvPlot.text = "Plot: ${drama.plot}"

        Glide.with(holder.itemView.context)
            .load(drama.photo)
            .into(holder.imgPhoto)

        holder.btnWiki.setOnClickListener {
            android.util.Log.d("DramaAdapter", "Tombol Wiki ditekan untuk: ${drama.title}")
            onWikiClick(drama.link)
        }

        holder.btnDetail.setOnClickListener {
            android.util.Log.d("DramaAdapter", "Tombol Detail ditekan untuk: ${drama.title}")
            onDetailClick(drama)
        }
    }
    fun updateData(newList: List<Drama>) {
        listDrama = newList
        notifyDataSetChanged()
    }
}
    fun updateData(dramas: List<Drama>) {
    }


