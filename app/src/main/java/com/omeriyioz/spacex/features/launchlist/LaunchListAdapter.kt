package com.omeriyioz.spacex.features.launchlist

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.omeriyioz.spacex.AllLaunchesQuery
import com.omeriyioz.spacex.R
import com.omeriyioz.spacex.features.launchdetail.LaunchDetailActivity

class LaunchListAdapter :
    PagingDataAdapter<AllLaunchesQuery.Launch, LaunchListAdapter.ViewHolder>(DiffCallback()) {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var textViewDetails: TextView =
            itemView.findViewById<View>(R.id.textViewDetails) as TextView

        fun bind(launch: AllLaunchesQuery.Launch) {
            textViewDetails.text = "ID : ${launch.id} \n" +
                    " Detail : ${launch.details} \n  " +
                    " Mission name : ${launch.mission_name} \n"

            itemView.setOnClickListener {
                 val intent = Intent(textViewDetails.context, LaunchDetailActivity::class.java)
                 intent.putExtra("launch_id", launch.id)
                 textViewDetails.context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val topicView = inflater.inflate(R.layout.item_launch, parent, false)
        return ViewHolder(topicView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        item?.let { holder.bind(it) }
    }
}

class DiffCallback : DiffUtil.ItemCallback<AllLaunchesQuery.Launch>() {
    override fun areItemsTheSame(
        oldItem: AllLaunchesQuery.Launch,
        newItem: AllLaunchesQuery.Launch
    ): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(
        oldItem: AllLaunchesQuery.Launch,
        newItem: AllLaunchesQuery.Launch
    ): Boolean {
        return oldItem == newItem
    }
}