package dev.wxlf.hammersystemstesttask.data.repository.remote

import com.google.gson.Gson
import dev.wxlf.hammersystemstesttask.data.api.ProductsApi
import dev.wxlf.hammersystemstesttask.data.model.ProductsListResponse

class RetrofitProductsListDataSource(val gson: Gson, val productsApi: ProductsApi) : ProductsListRemoteDataSource {
    override suspend fun getProductsList(): ProductsListResponse {
        return productsApi.getProductsList()
    }
}