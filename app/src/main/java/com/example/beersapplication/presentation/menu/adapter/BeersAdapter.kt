package com.example.beersapplication.presentation.menu.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.example.beersapplication.R
import com.example.beersapplication.databinding.ItemMenuBinding
import com.example.beersapplication.domain.entity.BeerEntity
import com.example.beersapplication.presentation.util.extentions.inflate

class BeersAdapter : RecyclerView.Adapter<BeersAdapter.BeerHolder>() {

    private val diffUtil = AsyncListDiffer(this, BeersCallback())

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BeerHolder {
        return BeerHolder(parent.inflate(R.layout.item_menu))
    }

    override fun onBindViewHolder(holder: BeerHolder, position: Int) {
        holder.onBind(diffUtil.currentList[position])
    }

    override fun getItemCount() = diffUtil.currentList.size

    fun setData(newData: List<BeerEntity>) {
        diffUtil.submitList(newData)
    }

    inner class BeerHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val viewModel by viewBinding(ItemMenuBinding::bind)

        fun onBind(beerEntity: BeerEntity) {
            with(viewModel) {
                Glide.with(root.context).load(beerEntity.url).into(beerImage)
                titleText.text = beerEntity.name
                descriptionText.text = beerEntity.description
                fortressText.text = root.context.getString(R.string.fortress, beerEntity.fortress)
            }
        }
    }
}

class BeersCallback : DiffUtil.ItemCallback<BeerEntity>() {
    override fun areItemsTheSame(oldItem: BeerEntity, newItem: BeerEntity): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: BeerEntity, newItem: BeerEntity): Boolean {
        return oldItem == newItem
    }
}
