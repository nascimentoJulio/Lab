package com.example.study.ui.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.study.R
import com.example.study.listeners.OnSeeDetailsListener
import com.example.study.models.TaskModel

class RecyclerTaskAdapter(tasks: MutableList<TaskModel>, listener: OnSeeDetailsListener) :
    RecyclerView.Adapter<RecyclerTaskViewHolder>() {

    private var mTasks = tasks
    private val mListener = listener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerTaskViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.task_item, parent, false)
        return RecyclerTaskViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerTaskViewHolder, position: Int) {
        holder.render(this.mTasks[position], this.mListener)
    }

    fun updateUI(list: MutableList<TaskModel>) {
        this.mTasks = list
        notifyDataSetChanged()
    }

    fun getItemByPosition(position: Int): TaskModel{
        return this.mTasks[position]
    }

    fun updateRemovedItem(position: Int){
        this.mTasks.removeAt(position)
        notifyItemRemoved(position)
    }
    override fun getItemCount(): Int {
        return this.mTasks.size
    }
}