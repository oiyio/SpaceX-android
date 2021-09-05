package com.omeriyioz.spacex.features.launchlist

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.omeriyioz.spacex.AllLaunchesQuery
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LaunchListViewModel @Inject constructor(
        var repository: LaunchListRepository
) : ViewModel(){

    private lateinit var _locationsFlow: Flow<PagingData<AllLaunchesQuery.Launch>>
    val locationsFlow: Flow<PagingData<AllLaunchesQuery.Launch>>
        get() = _locationsFlow

    init {
        getAllLaunches()
    }

    private fun getAllLaunches(){
        viewModelScope.launch {
            try {
                val result = repository.getAllLaunches()
                _locationsFlow = result
                Log.d("omertest", "try: ")
            } catch (ex: Exception) {
                Log.d("omertest", "catch: ")
            }
        }
    }

}