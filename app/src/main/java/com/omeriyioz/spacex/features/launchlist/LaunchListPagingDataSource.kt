package com.omeriyioz.spacex.features.launchlist

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.api.Input
import com.apollographql.apollo.coroutines.await
import com.omeriyioz.spacex.AllLaunchesQuery
import kotlinx.coroutines.delay
import javax.inject.Inject

const val START_INDEX = 0
const val LOAD_SIZE = 5

class LaunchListPagingDataSource @Inject constructor(
    private val client: ApolloClient
) : PagingSource<Int, AllLaunchesQuery.Launch>() {

    override fun getRefreshKey(state: PagingState<Int, AllLaunchesQuery.Launch>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }

    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, AllLaunchesQuery.Launch> {
        return try {
            val offset = (params.key ?: START_INDEX)
            val limit = LOAD_SIZE
            val response = getLaunchPadList(offset, limit)
            Log.d("omertest", "$offset $limit $response")
            val nextKey = if (response.isNullOrEmpty()) {
                null
            } else {
                offset + LOAD_SIZE
            }


            delay(2000)


            LoadResult.Page(response, null, nextKey)
        } catch (e: Exception) {
            LoadResult.Error(e)
        }

    }

    private suspend fun getLaunchPadList(offset: Int, limit: Int): List<AllLaunchesQuery.Launch> {
        val response =
            client.query(
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