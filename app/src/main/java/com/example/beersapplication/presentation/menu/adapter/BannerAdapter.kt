package com.example.beersapplication.presentation.menu.adapter

import android.view.View
import android.view.ViewGroup
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.beersapplication.R
import com.example.beersapplication.databinding.ItemBannerBinding
import com.example.beersapplication.presentation.util.extentions.inflate

class BannerAdapter : RecyclerView.Adapter<BannerAdapter.BannerHolder>() {

    private val images = listOf(R.drawable.rectangle1, R.drawable.rectangle2, R.drawable.rectangle3)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BannerHolder {
        return BannerHolder(parent.inflate(R.layout.item_banner))
    }

    override fun onBindViewHolder(holder: BannerHolder, position: Int) {
        holder.onBind(images[position])
    }

    override fun getItemCount() = images.size

    inner class BannerHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val viewBinding by viewBinding(ItemBannerBinding::bind)
        fun onBind(@DrawableRes drawableId: Int) {
            with(viewBinding) {
                bannerImage.setImageDrawable(ContextCompat.getDrawable(root.context, drawableId))
            }
        }
    }
}
