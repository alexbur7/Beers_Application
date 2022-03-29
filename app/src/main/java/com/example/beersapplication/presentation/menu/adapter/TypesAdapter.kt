package com.example.beersapplication.presentation.menu.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.beersapplication.R
import com.example.beersapplication.databinding.ItemTypeBinding
import com.example.beersapplication.domain.entity.TypeEntity
import com.example.beersapplication.presentation.util.extentions.activated
import com.example.beersapplication.presentation.util.extentions.inflate

class TypesAdapter(
    private val typeClick: (Int) -> Unit
) : RecyclerView.Adapter<TypesAdapter.TypeHolder>() {

    private val typesList = listOf(
        TypeEntity(true, R.string.beer),
        TypeEntity(textId = R.string.strong_beer),
        TypeEntity(textId = R.string.pizza),
        TypeEntity(textId = R.string.combo),
        TypeEntity(textId = R.string.desserts)
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TypeHolder {
        val viewHolder = TypeHolder(parent.inflate(R.layout.item_type))
        viewHolder.itemView.setOnClickListener {
            if (viewHolder.bindingAdapterPosition != RecyclerView.NO_POSITION) {
                typeClick(viewHolder.bindingAdapterPosition)
                typesList.forEachIndexed { index, typeEntity ->
                    if (typeEntity.isActivated) {
                        typeEntity.isActivated = false
                        notifyItemChanged(index)
                    }
                }
                typesList[viewHolder.bindingAdapterPosition].isActivated = true
                it.activated(true)
            }
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: TypeHolder, position: Int) {
        holder.onBind(typesList[position])
    }

    override fun getItemCount() = typesList.size

    inner class TypeHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val viewBinding by viewBinding(ItemTypeBinding::bind)

        fun onBind(typeEntity: TypeEntity) {
            with(viewBinding) {
                typeText.text = root.context.getString(typeEntity.textId)
                root.activated(typeEntity.isActivated)
            }
        }
    }
}
