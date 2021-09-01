package com.omeriyioz.spacex.di

import com.apollographql.apollo.ApolloClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    const val GRAPHQL_API_URL = "https://spacex-nestjs-server.herokuapp.com/graphql"

    @Singleton
    @Provides
    fun provideApolloClient(): ApolloClient {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        val okHttpClient = OkHttpClient
            .Builder()
            .addInterceptor(logging)

        return ApolloClient.builder()
            .serverUrl(GRAPHQL_API_URL)
            .okHttpClient(okHttpClient.build())
            .build()
    }
}

