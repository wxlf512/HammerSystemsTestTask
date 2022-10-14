package dev.wxlf.hammersystemstesttask.data.model

import com.google.gson.annotations.SerializedName

data class ProductsListResponse(@SerializedName("items") val items: List<ProductsListItem>) {
}

data class ProductsListItem(

    @SerializedName("id")
    val id: Int,
    @SerializedName("imageUrl")
    val imageUrl: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("quantity")
    val quantity: Int
) {
}
