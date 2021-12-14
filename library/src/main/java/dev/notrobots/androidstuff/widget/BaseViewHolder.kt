package dev.notrobots.androidstuff.widget

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder : RecyclerView.ViewHolder {
    constructor(itemView: View) : super(itemView)

    constructor(@LayoutRes layoutRes: Int, parent: ViewGroup) : super(
        LayoutInflater
            .from(parent.context)
            .inflate(layoutRes, parent, false)
    )
}
