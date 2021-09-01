package com.omeriyioz.spacex.features.launchdetail

import android.os.Bundle
import com.omeriyioz.spacex.BaseActivity
import com.omeriyioz.spacex.databinding.ActivityLaunchDetailBinding
import com.omeriyioz.spacex.viewbinding.viewBinding

class LaunchDetail : BaseActivity() {

    private val binding: ActivityLaunchDetailBinding by viewBinding()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

    }

}