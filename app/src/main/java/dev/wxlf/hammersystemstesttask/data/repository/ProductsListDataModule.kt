package dev.wxlf.hammersystemstesttask.data.repository

import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.wxlf.hammersystemstesttask.data.api.ProductsApi
import dev.wxlf.hammersystemstesttask.data.repository.local.ProductsRoomDatabase
import dev.wxlf.hammersystemstesttask.data.repository.local.ProductsListLocalDataSource
import dev.wxlf.hammersystemstesttask.data.repository.local.RoomProductsListDataSource
import dev.wxlf.hammersystemstesttask.data.repository.remote.ProductsListRemoteDataSource
import dev.wxlf.hammersystemstesttask.data.repository.remote.RetrofitProductsListDataSource
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ProductsListDataModule {

    @Provides
    @Singleton
    fun provideGson(): Gson = Gson()

    @Provides
    @Singleton
    fun provideLocalDataSource(gson: Gson, roomDatabase: ProductsRoomDatabase): ProductsListLocalDataSource =
        RoomProductsListDataSource(gson, roomDatabase.productsListDao())

    @Provides
    @Singleton
    fun provideRemoteDataSource(gson: Gson, productsApi: ProductsApi): ProductsListRemoteDataSource =
        RetrofitProductsListDataSource(gson, productsApi)

    @Provides
    @Singleton
    fun provideProductsListRepository(
        local: ProductsListLocalDataSource,
        remote: ProductsListRemoteDataSource
    ): ProductsListRepository = ProductsListRepository(local, remote)
}