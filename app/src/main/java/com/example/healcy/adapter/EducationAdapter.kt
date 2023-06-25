package com.example.healcy.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.healcy.R
import com.example.healcy.data.Education
import com.example.healcy.databinding.ItemEducationBinding
import com.example.healcy.response.DataArticle

class EducationAdapter(private var listArticle: List<Education>, private val onItemClick: (Education) -> Unit): RecyclerView.Adapter<EducationAdapter.ViewHolder>() {
    @SuppressLint("NotifyDataSetChanged")
    fun setData(education: List<Education>) {
        this.listArticle = education
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_education, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentArticle = listArticle[position]
        holder.bind(currentArticle)
    }

    override fun getItemCount(): Int {
        return listArticle.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val img: ImageView = itemView.findViewById(R.id.iv_item_img)
        private val title: TextView = itemView.findViewById(R.id.tv_item_title)
        private val author: TextView = itemView.findViewById(R.id.tv_item_author)

        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val clickedArticle = listArticle[position]
                    onItemClick.invoke(clickedArticle)
                }
            }
        }

        fun bind(education: Education) {
            title.text = education.title
            author.text = education.author
            Glide.with(itemView)
                .load(education.linkImage)
                .into(img)
        }
    }
}

//class EducationAdapter(private var listArticle: List<Education>): RecyclerView.Adapter<EducationAdapter.ViewHolder>() {
//
//    private lateinit var onItemClickCallback: OnItemClickCallback
//
//    interface OnItemClickCallback {
//        fun onItemClicked(data: Education)
//    }
//
//    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
//        this.onItemClickCallback = onItemClickCallback
//    }
//
//    @SuppressLint("NotifyDataSetChanged")
//    fun submitList(newlist: List<Education>){
//        listArticle = newlist
//        notifyDataSetChanged()
//    }
//
//    class ViewHolder(var binding: ItemEducationBinding) : RecyclerView.ViewHolder(binding.root){
//        fun binding(education: Education){
//            binding.tvItemTitle.text = education.title
//            binding.tvItemAuthor.text = education.author
//            Glide.with(itemView)
//                .load(education.linkImage)
//                .into(binding.ivItemImg)
//        }
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EducationAdapter.ViewHolder {
//        val binding = ItemEducationBinding.inflate(LayoutInflater.from(parent.context), parent, false)
//        return ViewHolder(binding)
//    }
//
//    override fun onBindViewHolder(holder: EducationAdapter.ViewHolder, position: Int) {
//        holder.binding(listArticle[position])
//        holder.itemView.setOnClickListener {
//            onItemClickCallback.onItemClicked(
//                listArticle[holder.adapterPosition]
//            )
//        }
//    }
//
//    override fun getItemCount(): Int {
//        return listArticle.size
//    }
//}