package dev.wxlf.hammersystemstesttask.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dev.wxlf.hammersystemstesttask.data.repository.local.ProductsRoomDatabase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RoomModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): ProductsRoomDatabase =
        Room.databaseBuilder(context, ProductsRoomDatabase::class.java, "products_room_database")
            .build()
}