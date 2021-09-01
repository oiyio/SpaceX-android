package com.omeriyioz.spacex.features.launchlist

import AllLaunchDetailsQuery
import android.os.Bundle
import android.util.Log
import android.view.View
import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.coroutines.toDeferred
import com.apollographql.apollo.exception.ApolloException
import com.omeriyioz.spacex.BaseActivity
import com.omeriyioz.spacex.databinding.ActivityLaunchListBinding
import com.omeriyioz.spacex.epoxy.entry
import com.omeriyioz.spacex.viewbinding.viewBinding
import dagger.hilt.android.AndroidEntryPoint
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
                binding.animationView.pauseAnimation()

                binding.animationView.visibility = View.INVISIBLE
                binding.loadingText.visibility = View.INVISIBLE
                binding.falcon.visibility = View.VISIBLE

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
        binding.animationView.playAnimation()
    }


}


