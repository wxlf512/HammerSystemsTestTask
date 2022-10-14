package dev.wxlf.hammersystemstesttask.data.api

import dev.wxlf.hammersystemstesttask.data.model.ProductsListResponse
import retrofit2.http.GET

interface ProductsApi {

    @GET("./main/products_list")
    suspend fun getProductsList(): ProductsListResponse
}