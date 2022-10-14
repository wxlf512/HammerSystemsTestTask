package dev.wxlf.hammersystemstesttask

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Rect
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class CategoryListAdapter(private val context: Context, private val list: List<String>) :
    RecyclerView.Adapter<CategoryListAdapter.ViewHolder>() {

    private var choosedPos = 0
    var onCategoryClick: ((Int) -> Unit)? = null

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val categoryName = itemView.findViewById<TextView>(R.id.category_name)

        init {
            itemView.setOnClickListener {
                onCategoryClick?.invoke(adapterPosition)
            }
        }
    }

    fun setChoosed(position: Int) {
        choosedPos = position
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.category_item, parent, false)
        return ViewHolder(itemView)
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.categoryName.text = list[position]
        if (position == choosedPos) {
            holder.categoryName.background =
                context.getDrawable(R.drawable.category_item_checked_bg)
            holder.categoryName.setTextColor(context.getColor(R.color.primaryText))
        } else {
            holder.categoryName.background = context.getDrawable(R.drawable.category_item_bg)
            holder.categoryName.setTextColor(context.getColor(R.color.category_unchecked))
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

}

class CategoryDivider(private val spacing: Int) :
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
                0 -> spacing
                else -> spacing / 2
            }
            rect.right = when (parent.getChildAdapterPosition(view)) {
                adapter.itemCount - 1 -> spacing
                else -> 0
            }
        }
    }
}