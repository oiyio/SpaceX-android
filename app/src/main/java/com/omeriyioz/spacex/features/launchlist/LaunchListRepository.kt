package com.omeriyioz.spacex.features.launchlist

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.apollographql.apollo.ApolloClient
import com.omeriyioz.spacex.AllLaunchesQuery
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LaunchListRepository @Inject constructor(
    private val client: ApolloClient
) {

    fun getAllLaunches(): Flow<PagingData<AllLaunchesQuery.Launch>> = Pager(
        PagingConfig(
            pageSize = LOAD_SIZE,
            enablePlaceholders = false,
            initialLoadSize = LOAD_SIZE
        )
    ) {
        LaunchListPagingDataSource(client)
    }.flow

}
