package com.example.mahasiswabiografi

import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class Adapter (private val dataList: ArrayList<Mahasiswa>
) : RecyclerView.Adapter<Adapter.MahasiswaViewHolder>() {

    var onItemClick: ((Mahasiswa) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MahasiswaViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return MahasiswaViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MahasiswaViewHolder, position: Int) {
        val currentItem = dataList[position]
        holder.image.setImageResource(currentItem.foto)
        holder.title.text = currentItem.nama

        holder.buttonDetail.setOnClickListener {
            onItemClick?.invoke(currentItem)
        }
    }

    override fun getItemCount(): Int = dataList.size

    class MahasiswaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.findViewById(R.id.image)
        val title: TextView = itemView.findViewById(R.id.title)
        val buttonDetail: TextView = itemView.findViewById(R.id.button_detail)
    }
}

