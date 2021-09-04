package com.omeriyioz.spacex.features.launchlist

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.omeriyioz.spacex.AllLaunchesQuery
import com.omeriyioz.common.ViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LaunchListViewModel @Inject constructor(
        var dataSource: LaunchListPagingDataSource,

) : ViewModel(){

    val flow = Pager(
        PagingConfig(
            pageSize = LOAD_SIZE,
            enablePlaceholders = false,
            initialLoadSize = LOAD_SIZE
        )
    ) {
        dataSource
    }.flow


    private val _allLaunches by lazy { MutableLiveData<ViewState<AllLaunchesQuery.Data>>() }
    val allLaunches: LiveData<ViewState<AllLaunchesQuery.Data>>
        get() = _allLaunches

    /*fun fetchDataFromServer() {
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

        }
    }*/
}