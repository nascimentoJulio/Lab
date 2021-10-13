package com.example.study.ui.recycler

import android.view.View
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.study.R
import com.example.study.R.drawable.icon_incomplete
import com.example.study.listeners.OnSeeDetailsListener
import com.example.study.models.TaskModel

class RecyclerTaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun render(task: TaskModel, listener: OnSeeDetailsListener){
        val title = itemView.findViewById<TextView>(R.id.text_title)
        val dueDate = itemView.findViewById<TextView>(R.id.text_date)
        val topic = itemView.findViewById<TextView>(R.id.text_item_topic)
        val description = itemView.findViewById<TextView>(R.id.text_item_description)
        val card = itemView.findViewById<CardView>(R.id.card_task)
        val status = itemView.findViewById<View>(R.id.status)

        if (task.title.length > 30)
            title.text = task.title.substring(0, 30) + "..."
        else
            title.text = task.title

        if (task.isCompleted) {
            status.setBackgroundResource(R.drawable.icon_completed)
        }else{
            status.setBackgroundResource(icon_incomplete)
        }

        dueDate.text = task.dueDate
        topic.text = task.topic
        if (task.description.length > 35)
            description.text = task.description.substring(0, 35) + "..."
        else
            description.text = task.description


        card.setOnClickListener {
            listener.onClick(task.id)
        }

    }
}