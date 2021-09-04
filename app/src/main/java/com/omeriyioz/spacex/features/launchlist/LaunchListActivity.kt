package com.omeriyioz.spacex.features.launchlist

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.omeriyioz.common.ViewState
import com.omeriyioz.common.viewBinding
import com.omeriyioz.spacex.BaseActivity
import com.omeriyioz.spacex.databinding.ActivityLaunchListBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class LaunchListActivity : BaseActivity() {

    val binding: ActivityLaunchListBinding by viewBinding()

    private val viewModel: LaunchListViewModel by viewModels()

    val adapter = LaunchListAdapter()

    private var job: Job? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        /*showLottieAnimation()*/

        binding.recyclerViewLaunchList.adapter = adapter
        binding.recyclerViewLaunchList.layoutManager = LinearLayoutManager(this)


        // viewModel.fetchDataFromServer()

        // observeLiveData()

        observeLaunchListDataFlow()
    }

    private fun observeLaunchListDataFlow() {
        job = lifecycleScope.launch {
            viewModel.flow.collectLatest {
                adapter.submitData(it)
            }
        }
    }

    /*private fun observeLiveData() {

        viewModel.allLaunches.observe(this) { response ->
            when (response) {
                is ViewState.Loading -> {
                    *//* binding.charactersRv.visibility = View.GONE
                     binding.charactersFetchProgress.visibility = View.VISIBLE*//*
                }
                is ViewState.Success -> {
                    val list = response.result.launches?.map {
                        LaunchItem(id = it?.id?: "",
                                details = it?.details?: "",
                                launch_date_local = it?.launch_date_local.toString(),
                                mission_name = it?.mission_name?:""
                        )
                    }
                    adapter.submitList(list)
                    Log.d("omertest", "observeLiveData: ")
                    //
                    *//*if (response.result.characters?.results?.size == 0) {
                        characterAdapter.submitList(emptyList())
                        binding.charactersFetchProgress.visibility = View.GONE
                        binding.charactersRv.visibility = View.GONE
                        binding.charactersEmptyText.visibility = View.VISIBLE
                    } else {
                        binding.charactersRv.visibility = View.VISIBLE
                        binding.charactersEmptyText.visibility = View.GONE
                    }
                    val results = response.result.characters?.results
                    characterAdapter.submitList(results)
                    binding.charactersFetchProgress.visibility = View.GONE*//*
                }
                is ViewState.Error -> {
                    *//*characterAdapter.submitList(emptyList())
                    binding.charactersFetchProgress.visibility = View.GONE
                    binding.charactersRv.visibility = View.GONE
                    binding.charactersEmptyText.visibility = View.VISIBLE*//*
                }
            }
        }
    }*/

    /* private fun showLottieAnimation() {
         binding.animationView.playAnimation()
     }
 */

}


