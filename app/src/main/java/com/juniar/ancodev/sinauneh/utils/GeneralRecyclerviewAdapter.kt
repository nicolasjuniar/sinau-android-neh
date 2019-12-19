package com.juniar.ancodev.sinauneh.utils

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView

class GeneralRecyclerViewAdapter<T>(
    @LayoutRes private val resId: Int, private val rooms: MutableList<T>,
    private val listener: (T, position: Int, View) -> Unit,
    private val viewHolderBindAction: (T, View) -> Unit
) : RecyclerView.Adapter<GeneralRecyclerViewAdapter.GeneralViewHolder<T>>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        GeneralViewHolder<T>(LayoutInflater.from(parent.context).inflate(resId, parent, false))

    override fun onBindViewHolder(holder: GeneralViewHolder<T>, position: Int) {
        holder.bind(rooms[position], listener, viewHolderBindAction)
    }

    override fun getItemCount() = rooms.size

    class GeneralViewHolder<T>(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(any: T, listener: (T, position: Int, View) -> Unit, viewHolderBindAction: (T, View) -> Unit) =
            with(itemView) {
                viewHolderBindAction(any, itemView)
                setOnClickListener { listener(any, adapterPosition, itemView) }
            }
    }
}