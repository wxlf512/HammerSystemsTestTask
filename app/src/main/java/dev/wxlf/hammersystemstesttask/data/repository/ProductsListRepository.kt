package dev.wxlf.hammersystemstesttask.data.repository

import dev.wxlf.hammersystemstesttask.data.model.ProductsListResponse
import dev.wxlf.hammersystemstesttask.data.repository.local.ProductsListLocalDataSource
import dev.wxlf.hammersystemstesttask.data.repository.remote.ProductsListRemoteDataSource

class ProductsListRepository (
    private val productsListLocalDataSource: ProductsListLocalDataSource,
    private val productsListRemoteDataSource: ProductsListRemoteDataSource
        ){

    suspend fun fetchQuestList(): ProductsListResponse = productsListRemoteDataSource.getProductsList()
}