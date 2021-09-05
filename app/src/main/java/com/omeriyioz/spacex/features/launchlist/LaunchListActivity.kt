package com.omeriyioz.spacex.features.launchlist

import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
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
            setHasFixedSize(true)
            adapter = launchListAdapter.withLoadStateFooter(
                footer = LaunchListLoadStateAdapter { /*adapter.retry()*/ }
            )
        }

        binding.swipeRefresh.setOnRefreshListener {
            launchListAdapter.refresh()
        }

        observeLaunchListDataFlow()

        observeViewState()
    }

    private fun observeLaunchListDataFlow() {
        job = lifecycleScope.launchWhenCreated {
            viewModel.locationsFlow.collectLatest {
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

        lifecycleScope.launch {
            launchListAdapter.loadStateFlow.collectLatest {
                binding.swipeRefresh.isRefreshing = it.refresh is LoadState.Loading
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