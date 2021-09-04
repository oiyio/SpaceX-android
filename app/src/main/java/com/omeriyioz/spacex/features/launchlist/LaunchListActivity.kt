package com.omeriyioz.spacex.features.launchlist

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.omeriyioz.common.viewBinding
import com.omeriyioz.spacex.BaseActivity
import com.omeriyioz.spacex.databinding.ActivityLaunchListBinding
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChangedBy
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch


class LaunchListActivity : BaseActivity() {

    val binding: ActivityLaunchListBinding by viewBinding()

    private val viewModel: LaunchListViewModel by viewModels()

    val launchListAdapter = LaunchListAdapter()

    private var job: Job? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        /*showLottieAnimation()*/

        binding.recyclerViewLaunchList.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = launchListAdapter.withLoadStateFooter(
                footer = MovieLoadStateAdapter { /*adapter.retry()*/ }
            )
        }

        observeLaunchListDataFlow()

        observeViewState()
    }

    private fun observeLaunchListDataFlow() {
        job = lifecycleScope.launch {
            viewModel.flow.collectLatest {
                launchListAdapter.submitData(it)
            }
        }
    }

    private fun observeViewState() {
        lifecycleScope.launch {
            launchListAdapter.loadStateFlow
                // Only emit when REFRESH LoadState changes.
                .distinctUntilChangedBy { it.refresh }
                // Only react to cases where REFRESH completes i.e., NotLoading.
                .filter { it.refresh is LoadState.NotLoading }
                .collect {
                    binding.recyclerViewLaunchList.scrollToPosition(0)
                }
        }

        /*lifecycleScope.launch {
            launchListAdapter.loadStateFlow.collectLatest { loadState ->
                if (loadState.append.endOfPaginationReached) {
                    if (launchListAdapter.itemCount < 1) {
                        Log.d("omertest", "endOfPaginationReached: ")
                    }
                }
            }
        }*/

    }


}