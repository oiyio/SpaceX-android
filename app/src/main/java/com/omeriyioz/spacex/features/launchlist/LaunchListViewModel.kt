package com.omeriyioz.spacex.features.launchlist

import androidx.lifecycle.ViewModel

class LaunchListViewModel : ViewModel(){
    var launchesRespository : LaunchesRespository? = null

    init {
        launchesRespository = LaunchesRespository()
    }
}