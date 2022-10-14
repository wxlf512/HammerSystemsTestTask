package dev.wxlf.hammersystemstesttask.adapters

import android.graphics.Rect
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dev.wxlf.hammersystemstesttask.R


class BannerListAdapter(private val list: List<Int>) :
    RecyclerView.Adapter<BannerListAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.banner_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    }

    override fun getItemCount(): Int {
        return list.size
    }

}

class BannerDivider(private val spacing: Int) :
    RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        rect: Rect,
        view: View,
        parent: RecyclerView,
        s: RecyclerView.State
    ) {
        parent.adapter?.let { adapter ->
            rect.left = when (parent.getChildAdapterPosition(view)) {
                RecyclerView.NO_POSITION -> 0
                else -> spacing
            }
            rect.right = when (parent.getChildAdapterPosition(view)) {
                adapter.itemCount - 1 -> spacing
                else -> 0
            }
        }
    }
}