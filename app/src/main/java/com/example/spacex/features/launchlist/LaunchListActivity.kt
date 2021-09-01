package com.example.spacex.features.launchlist

import AllLaunchDetailsQuery
import android.os.Bundle
import android.util.Log
import android.view.View
import com.airbnb.epoxy.EpoxyRecyclerView
import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.coroutines.toDeferred
import com.apollographql.apollo.exception.ApolloException
import com.example.spacex.BaseActivity
import com.example.spacex.R
import com.example.spacex.databinding.ActivityLaunchListBinding
import com.example.spacex.epoxy.entry
import com.example.spacex.viewbinding.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_launch_list.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class LaunchListActivity : BaseActivity() {

    @Inject
    lateinit var client: ApolloClient

    val binding: ActivityLaunchListBinding by viewBinding()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        showLottieAnimation()

        val scope = CoroutineScope(Dispatchers.Main + Job())

        fetchDataFromServer(scope)
    }

    private fun fetchDataFromServer(scope: CoroutineScope) {
        scope.launch {
            val response = try {
                client.query(AllLaunchDetailsQuery()).toDeferred().await()
            } catch (e: ApolloException) {
                fetchDataFromServer(this)
                return@launch
            }

            val launches = response.data?.launches()?.reversed()

            if (launches == null || response.hasErrors()) {
                // handle application errors
                return@launch
            } else {
                animation_view.pauseAnimation()

                animation_view.visibility = View.INVISIBLE
                loading_text.visibility = View.INVISIBLE
                falcon.visibility = View.VISIBLE

                binding.epoxyList.withModels {

                    launches.forEach {
                        Log.d("how many", ":${it} ")
                        entry {
                            id(hashCode())
                            name(it?.site())
                            date(it?.launch_date_utc().toString())
                            mission(it?.mission()?.name())
                        }
                    }

                }
            }

        }
    }

    private fun showLottieAnimation() {
        animation_view.playAnimation()
    }


}


