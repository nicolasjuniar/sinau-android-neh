package id.nyoman.core.utils

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import id.nyoman.core.State

class GeneralPagedListAdapter<T : Any>(
    diffCallback: DiffUtil.ItemCallback<T>,
    @LayoutRes private val resId: Int,
    private val listener: (T, position: Int, View) -> Unit,
    private val viewHolderBindAction: (T, View) -> Unit
) : PagedListAdapter<T, GeneralPagedListAdapter.GeneralViewHolder<T>>(diffCallback) {

    private var state = State.LOADING

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        GeneralViewHolder<T>(
            LayoutInflater.from(parent.context).inflate(
                resId,
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: GeneralViewHolder<T>, position: Int) {
        holder.bind(getItem(position), listener, viewHolderBindAction)
    }

    class GeneralViewHolder<T>(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(
            any: T?,
            listener: (T, position: Int, View) -> Unit,
            viewHolderBindAction: (T, View) -> Unit
        ) =
            with(itemView) {
                any?.let {
                    viewHolderBindAction(any, itemView)
                    setOnClickListener { listener(any, adapterPosition, itemView) }
                }
            }
    }

    fun setState(state: State) {
        this.state = state
        notifyItemChanged(super.getItemCount())
    }
}