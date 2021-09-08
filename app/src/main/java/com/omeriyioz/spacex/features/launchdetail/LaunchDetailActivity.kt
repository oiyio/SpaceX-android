package com.omeriyioz.spacex.features.launchdetail

import android.os.Bundle
import androidx.activity.viewModels
import com.omeriyioz.common.viewBinding
import com.omeriyioz.spacex.BaseActivity
import com.omeriyioz.spacex.databinding.ActivityLaunchDetailBinding

class LaunchDetailActivity : BaseActivity() {

    private val binding: ActivityLaunchDetailBinding by viewBinding()

    private val viewModel: LaunchDetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        viewModel.launchDetail.observe(this) {
            binding.textViewLaunchDetail.text = "ID : ${it.id} \nDetails : ${it.details}"
        }

        intent.getStringExtra("launch_id")?.let {
            viewModel.getLaunch(it)
        }

    }

}