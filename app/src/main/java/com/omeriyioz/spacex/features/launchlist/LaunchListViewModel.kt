package com.omeriyioz.spacex.features.launchlist

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.omeriyioz.spacex.AllLaunchesQuery
import com.omeriyioz.spacex.ViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LaunchListViewModel @Inject constructor(
        var repository: LaunchesRepository
) : ViewModel(){

    private val _allLaunches by lazy { MutableLiveData<ViewState<AllLaunchesQuery.Data>>() }
    val allLaunches: LiveData<ViewState<AllLaunchesQuery.Data>>
        get() = _allLaunches

    fun fetchDataFromServer() {
        viewModelScope.launch {

            val response = repository.fetchDataFromServer()

            response.let { data ->
                when (data) {
                    is ViewState.Success -> {
                        _allLaunches.postValue(data)
                        Log.d("queryCharactersList()", "response: $data")
                    }
                    is ViewState.Error -> {
                        _allLaunches.postValue(data)
                        Log.e("queryCharacter(id)", "error block")
                    }
                    else -> {
                        _allLaunches.postValue(data)
                        Log.e("queryCharactersList()", "catch block")
                    }
                }
            }

          /*  val response = try {
                client.query(AllLaunchesQuery()).toDeferred().await()
            } catch (e: ApolloException) {
                fetchDataFromServer(this)
                return@launch
            }

            val launches = response.data?.launches?.reversed()*/

            Log.d("omertest", "fetchDataFromServer: ")

            /*if (launches == null || response.hasErrors()) {
                // handle application errors
                return@launch
            } else {
                binding.animationView.pauseAnimation()

                binding.animationView.visibility = View.INVISIBLE
                binding.loadingText.visibility = View.INVISIBLE
                binding.falcon.visibility = View.VISIBLE

                binding.epoxyList.withModels {

                    launches.forEach {
                        Log.d("omertest", ":${it} ")
                        entry {
                            *//*id(hashCode())
                            name(it?.site())
                            date(it?.launch_date_utc().toString())
                            mission(it?.mission()?.name())*//*
                        }
                    }

                }
            }*/

        }
    }
}