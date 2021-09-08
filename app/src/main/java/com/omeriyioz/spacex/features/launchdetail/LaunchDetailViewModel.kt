package com.omeriyioz.spacex.features.launchdetail
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.api.Input
import com.apollographql.apollo.coroutines.await
import com.omeriyioz.spacex.AllLaunchesQuery
import com.omeriyioz.spacex.LaunchDetailsQuery
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LaunchDetailViewModel @Inject constructor(
    private var client: ApolloClient
) : ViewModel(){

    val launchDetail : MutableLiveData<LaunchDetailsQuery.Launch> = MutableLiveData()

    fun getLaunch(launchId : String){
        viewModelScope.launch {
            try {
                val response = client.query(
                        LaunchDetailsQuery(
                            id = launchId
                        )
                    ).await()
                if (!response.hasErrors() && response.data != null) {
                    val launch = response.data?.launch!!
                    launchDetail.value = launch
                } else {
                    throw Exception(response.errors?.toString())
                }

                Log.d("omertest", "try: ")
            } catch (ex: Exception) {
                Log.d("omertest", "catch: ")
            }
        }
    }

}