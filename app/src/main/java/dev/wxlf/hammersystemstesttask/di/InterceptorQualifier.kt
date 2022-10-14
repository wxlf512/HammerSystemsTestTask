package dev.wxlf.hammersystemstesttask.di

import javax.inject.Qualifier

interface InterceptorQualifier {
    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class OfflineInterceptor

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class OnlineInterceptor
}