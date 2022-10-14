package dev.wxlf.hammersystemstesttask.data.repository.local

import com.google.gson.Gson
import dev.wxlf.hammersystemstesttask.data.repository.local.dao.ProductsListDao

class RoomProductsListDataSource(val gson: Gson, val productsListDao: ProductsListDao) : ProductsListLocalDataSource {
}