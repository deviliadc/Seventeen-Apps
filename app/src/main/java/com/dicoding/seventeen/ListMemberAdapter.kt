package com.dicoding.seventeen

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class ListMemberAdapter(private val listMember: ArrayList<Member>) : RecyclerView.Adapter<ListMemberAdapter.ListViewHolder>() {
    interface OnItemClickCallback {
        fun onItemClicked(data: Member)
    }

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgPhoto: ImageView = itemView.findViewById(R.id.img_item_photo)
        val tvName: TextView = itemView.findViewById(R.id.tv_item_name)
        val tvDescription: TextView = itemView.findViewById(R.id.tv_item_description)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_row_member, parent, false)
        return ListViewHolder(view)
    }

    override fun getItemCount(): Int = listMember.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val member = listMember[position]

        Glide.with(holder.itemView.context)
            .load(member.photo)
            .apply(RequestOptions().override(100, 100))
            .into(holder.imgPhoto)
        holder.tvName.text = member.name
        holder.tvDescription.text = member.description

        holder.itemView.setOnClickListener {
            onItemClickCallback.onItemClicked(member)
        }
    }
}