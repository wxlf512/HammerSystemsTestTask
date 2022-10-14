package dev.wxlf.hammersystemstesttask.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import dev.wxlf.hammersystemstesttask.R
import dev.wxlf.hammersystemstesttask.data.model.ProductsListItem
import dev.wxlf.hammersystemstesttask.data.model.ProductsListResponse


class ProductListAdapter() :
    RecyclerView.Adapter<ProductListAdapter.ViewHolder>() {

    private var productsList: ProductsListResponse = ProductsListResponse(listOf(ProductsListItem(0, "", "", "", 0)))

    fun updateData(newProductsList: ProductsListResponse) {
       productsList = newProductsList
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val productName = itemView.findViewById<TextView>(R.id.product_name)!!
        val productDescription = itemView.findViewById<TextView>(R.id.product_description)!!
        val productImage = itemView.findViewById<ImageView>(R.id.product_image)!!
        val productBuyButton = itemView.findViewById<Button>(R.id.product_buy_button)!!
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.products_item, parent, false)
        return ViewHolder(itemView)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.productName.text = productsList.items[position].name
        holder.productDescription.text = productsList.items[position].description
        holder.productBuyButton.text = "от ${productsList.items[position].quantity} р"
        val imgUrl = productsList.items[position].imageUrl
        if (imgUrl.isNotEmpty())
            Picasso.get().load(imgUrl)
                .placeholder(R.drawable.product_placeholder)
                .error(R.drawable.product_placeholder)
                .into(holder.productImage)
    }

    override fun getItemCount(): Int {
        return productsList.items.size
    }

}