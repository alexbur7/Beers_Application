package com.example.beersapplication.presentation.util.factory

import android.graphics.Canvas
import androidx.core.view.forEachIndexed
import androidx.recyclerview.widget.RecyclerView
import javax.inject.Inject

class DecorationFactory @Inject constructor() {

    fun create(
        leftMargin: Int = 0,
        rightMargin: Int = 0,
        topMargin: Int = 0,
        bottomMargin: Int = 0
    ) = object : RecyclerView.ItemDecoration() {
        override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
            super.onDrawOver(c, parent, state)

            parent.forEachIndexed { position, view ->
                val params = view.layoutParams as RecyclerView.LayoutParams
                params.setMargins(
                    leftMargin, topMargin,
                    if (position != parent.childCount - 1) 0
                    else rightMargin,
                    bottomMargin
                )
                view.layoutParams = params
            }
        }
    }
}
