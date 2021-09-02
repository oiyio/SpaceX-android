package com.omeriyioz.spacex.features.launchlist


import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.omeriyioz.spacex.R

class LaunchesAdapter : ListAdapter<LaunchItem, LaunchesAdapter.ViewHolder>(DiffCallback()) {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var textViewDetails: TextView = itemView.findViewById<View>(R.id.textViewDetails) as TextView

        fun bind(LaunchItem: LaunchItem) {
            textViewDetails.text = "ID : ${LaunchItem.id} \n" +
                    " Detail : ${LaunchItem.details} \n  " +
                    " Mission name : ${LaunchItem.mission_name} \n"

            itemView.setOnClickListener {
               /* val intent = Intent(textViewDetails.context, QuestionActivity::class.java)
                intent.putExtra("topic_title", LaunchItem.name)
                textViewDetails.context.startActivity(intent)*/
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val topicView = inflater.inflate(R.layout.item_launch, parent, false)
        return ViewHolder(topicView)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.bind(getItem(position))
    }

    fun getNoteItem(position: Int): LaunchItem {
        return getItem(position)
    }

    class DiffCallback : DiffUtil.ItemCallback<LaunchItem>() {
        override fun areItemsTheSame(oldItem: LaunchItem, newItem: LaunchItem): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: LaunchItem, newItem: LaunchItem): Boolean {
            return oldItem == newItem
        }
    }
}