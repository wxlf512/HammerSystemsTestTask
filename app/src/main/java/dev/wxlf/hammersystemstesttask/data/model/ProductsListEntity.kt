package dev.wxlf.hammersystemstesttask.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import dev.wxlf.hammersystemstesttask.data.model.ProductsListEntity.Companion.TABLE_NAME

@Entity(tableName = TABLE_NAME)
data class ProductsListEntity(
    @PrimaryKey @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "imageUrl")
    val imageUrl: String,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "description")
    val description: String,
    @ColumnInfo(name = "quantity")
    val quantity: Int
) {
    companion object {
        const val TABLE_NAME = "products_list"
    }
}
