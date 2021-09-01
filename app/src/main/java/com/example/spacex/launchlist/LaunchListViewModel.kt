package com.example.spacex.launchlist

import androidx.lifecycle.ViewModel

class LaunchListViewModel : ViewModel(){
    var launchesRespository : LaunchesRespository? = null

    init {
        launchesRespository = LaunchesRespository()
    }
}