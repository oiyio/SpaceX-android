package com.example.spacex.features.launchdetail

import android.os.Bundle
import com.example.spacex.BaseActivity
import com.example.spacex.databinding.ActivityLaunchDetailBinding
import com.example.spacex.viewbinding.viewBinding

class LaunchDetail : BaseActivity() {

    private val binding: ActivityLaunchDetailBinding by viewBinding()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


    }

}