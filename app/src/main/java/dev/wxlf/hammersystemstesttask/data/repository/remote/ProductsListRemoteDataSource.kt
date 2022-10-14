package dev.wxlf.hammersystemstesttask.data.repository.remote

import dev.wxlf.hammersystemstesttask.data.model.ProductsListResponse

interface ProductsListRemoteDataSource {
    suspend fun getProductsList(): ProductsListResponse
}
