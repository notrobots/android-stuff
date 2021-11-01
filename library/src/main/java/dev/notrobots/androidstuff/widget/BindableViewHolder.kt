package dev.notrobots.androidstuff.widget

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView

abstract class BindableViewHolder<T>(itemView: View) : RecyclerView.ViewHolder(itemView) {
    constructor(@LayoutRes layoutRes: Int, parent: ViewGroup) : this(
            LayoutInflater.from(parent.context).inflate(layoutRes, parent, false)
    )

    abstract fun bind(item: T)
}