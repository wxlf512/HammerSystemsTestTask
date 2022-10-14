package dev.wxlf.hammersystemstesttask.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dev.wxlf.hammersystemstesttask.data.api.ProductsApi
import dev.wxlf.hammersystemstesttask.util.CacheUtil
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RetrofitModule {

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return httpLoggingInterceptor
    }

    @Provides
    @Singleton
    @InterceptorQualifier.OnlineInterceptor
    fun provideOnlineInterceptor(): Interceptor =
        Interceptor {
            val response = it.proceed(it.request())
            val maxAge = 60
            return@Interceptor response.newBuilder()
                .header("Cache-Control", "public, max-age=" + maxAge)
                .removeHeader("Pragma")
                .build()
        }

    @Provides
    @Singleton
    @InterceptorQualifier.OfflineInterceptor
    fun provideOfflineInterceptor(@ApplicationContext context: Context): Interceptor =
        Interceptor {
            var request = it.request()
            if (!CacheUtil().isInternetAvailable(context)) {
                val maxStale = 60 * 60 * 24 * 7
                request = request.newBuilder()
                    .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                    .removeHeader("Pragma")
                    .build()
            }
            return@Interceptor it.proceed(request)
        }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        @ApplicationContext context: Context, /* httpLoggingInterceptor: HttpLoggingInterceptor */
        @InterceptorQualifier.OfflineInterceptor offlineInterceptor: Interceptor,
        @InterceptorQualifier.OnlineInterceptor onlineInterceptor: Interceptor
    ): OkHttpClient {
        val cacheSize = (100 * 1024 * 1024).toLong() // 100 MB
        val cache = Cache(context.cacheDir, cacheSize)
        return OkHttpClient.Builder()
//            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor(offlineInterceptor)
            .addNetworkInterceptor(onlineInterceptor)
            .cache(cache)
            .build()
    }

    @Provides
    @Singleton
    fun provideGsonConverterFactory(): GsonConverterFactory = GsonConverterFactory.create()

    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory
    ): Retrofit = Retrofit.Builder()
        .baseUrl("https://raw.githubusercontent.com/wxlf512/temp_pizza_api/")
        .client(okHttpClient)
        .addConverterFactory(gsonConverterFactory)
        .build()

    @Provides
    @Singleton
    fun provideProductsApi(retrofit: Retrofit): ProductsApi =
        retrofit.create(ProductsApi::class.java)

}