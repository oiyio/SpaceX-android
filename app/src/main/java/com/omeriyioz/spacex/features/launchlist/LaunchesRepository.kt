package com.omeriyioz.spacex.features.launchlist

import android.util.Log
import com.apollographql.apollo.ApolloClient
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
class LaunchesRepository @Inject constructor(
        private val client: ApolloClient
) {

    suspend fun fetchDataFromServer(): ViewState<AllLaunchesQuery.Data>? {
        var result: ViewState<AllLaunchesQuery.Data>? = null
        try {
            val response = client.query(AllLaunchesQuery()).await()
            response.let {
                it.data?.let { data -> result = handleSuccess(data) }
            }
        } catch (ae: ApolloException) {
            Log.e("CharacterRepositoryImpl", "Error: ${ae.message}")
            return handleException(GENERAL_ERROR_CODE)
        }
        return result
    }

}
