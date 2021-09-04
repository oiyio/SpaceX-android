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

        observeViewState()
    }

    private fun observeLaunchListDataFlow() {
        job = lifecycleScope.launch {
            viewModel.flow.collectLatest {
                adapter.submitData(it)
            }
        }
    }

    private fun observeViewState() {
        lifecycleScope.launch {
            adapter.loadStateFlow
                // Only emit when REFRESH LoadState changes.
                .distinctUntilChangedBy { it.refresh }
                // Only react to cases where REFRESH completes i.e., NotLoading.
                .filter { it.refresh is LoadState.NotLoading }
                .collect {
                    binding.recyclerViewLaunchList.scrollToPosition(0)
                }
        }
        lifecycleScope.launch {
            adapter.loadStateFlow.collectLatest { loadState ->

                Log.d("omertest-- 2 ", " " + loadState.append)
                when (loadState.append) {
                    is LoadState.Loading -> {
                        showLoadingView()
                        Log.d("omertest", "Loading: ")
                    }
                    is LoadState.NotLoading -> {
                        Log.d("omertest", "NotLoading: ")
                        showNewData()
                    }
                    is LoadState.Error -> {
                        Log.d("omertest", "Error: ")
                        Snackbar.make(
                            binding.recyclerViewLaunchList,
                            "error",
                            Snackbar.LENGTH_SHORT
                        ).show()
                        showNoContentView()
                    }
                }
                if (loadState.append.endOfPaginationReached) {
                    if (adapter.itemCount < 1) {
                        Log.d("omertest", "Loading: ")
                        showNoContentView()
                    }
                }
            }
        }
    }

    private fun showNewData() {
       /* binding.ivEmpty.visibility = View.GONE*/
        binding.progressBar.visibility = View.GONE
    }

    private fun showLoadingView() {
        /*binding.ivEmpty.visibility = View.GONE*/
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun showNoContentView() {
        Snackbar.make(
            binding.recyclerViewLaunchList,
            "Content not found",
            Snackbar.LENGTH_SHORT
        ).show()
        /*binding.ivEmpty.visibility = View.VISIBLE*/
        binding.progressBar.visibility = View.GONE
    }

}