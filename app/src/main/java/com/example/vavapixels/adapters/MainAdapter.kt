package com.example.vavapixels.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.vavapixels.R
import com.example.vavapixels.databinding.ResItemPixelBinding
import com.example.vavapixels.models.Pixel

class MainAdapter(private val onItemClicked: (Pixel) -> Unit) : RecyclerView.Adapter<MainViewHolder>() {

    private var pixels = mutableListOf<Pixel>()

    fun setPixelList(pixels: List<Pixel>) {

        this.pixels = pixels.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ResItemPixelBinding.inflate(inflater, parent, false)
        return MainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val pixel = pixels[position]
        holder.bind(pixel, onItemClicked)
    }

    override fun getItemCount(): Int {
        return pixels.size
    }
}

class MainViewHolder(val binding: ResItemPixelBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(pixel: Pixel, onItemClicked: (Pixel) -> Unit) {

        binding.titulo.text = pixel.titulo
        binding.autor.text = pixel.autor

        val requestOptions = RequestOptions()
            .placeholder(R.drawable.ic_launcher_background)
            .error(R.drawable.ic_launcher_background)

        Glide.with(itemView.context)
            .applyDefaultRequestOptions(requestOptions)
            .load(pixel.thumbnailUrl)
            .into(binding.thumbnail)

        itemView.setOnClickListener {
            onItemClicked(pixel)
        }

    }

}