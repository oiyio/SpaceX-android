package com.omeriyioz.spacex.features.launchlist

data class LaunchItem(val id: String,
                      val details: String,
                      val launch_date_local: String,
                      val mission_name: String
)