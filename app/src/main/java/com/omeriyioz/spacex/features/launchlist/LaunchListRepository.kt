package com.omeriyioz.spacex.features.launchlist

import android.util.Log
import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.api.Input
import com.apollographql.apollo.coroutines.await
import com.apollographql.apollo.exception.ApolloException
import com.omeriyioz.spacex.AllLaunchesQuery
import com.omeriyioz.spacex.BaseRepository.Companion.GENERAL_ERROR_CODE
import com.omeriyioz.spacex.BaseRepository.Companion.handleException
import com.omeriyioz.spacex.BaseRepository.Companion.handleSuccess
import com.omeriyioz.common.ViewState
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LaunchListRepository @Inject constructor(
        private val client: ApolloClient
) {

    suspend fun getLaunchPadList(offset: Int, limit: Int): List<AllLaunchesQuery.Launch> {
        val response =
            client .query(
                AllLaunchesQuery(
                    offset = Input.fromNullable(offset),
                    limit = Input.fromNullable(limit)
                )
            ).await()
        return if (!response.hasErrors() && response.data != null) {
            val launchpads = response.data?.launches!!
            launchpads.filterNotNull()
        } else {
            throw Exception(response.errors?.toString())
        }
    }

}
