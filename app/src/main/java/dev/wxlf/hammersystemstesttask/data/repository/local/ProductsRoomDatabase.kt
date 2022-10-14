package dev.wxlf.hammersystemstesttask.data.repository.local

import androidx.room.Database
import androidx.room.RoomDatabase
import dev.wxlf.hammersystemstesttask.data.model.ProductsListEntity
import dev.wxlf.hammersystemstesttask.data.repository.local.dao.ProductsListDao

@Database(
    entities = [
        ProductsListEntity::class
    ], version = 1, exportSchema = true
)
abstract class ProductsRoomDatabase : RoomDatabase() {

    abstract fun productsListDao(): ProductsListDao
}