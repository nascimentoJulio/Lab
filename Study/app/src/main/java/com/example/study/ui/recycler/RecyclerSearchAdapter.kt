package com.example.study.ui.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.study.R
import com.example.study.listeners.OnClickLastSearch
import com.example.study.listeners.OnDeleteSearch
import com.example.study.models.SearchModel

class RecyclerSearchAdapter(
    searches: MutableList<SearchModel>,
    deleteClick: OnDeleteSearch,
    searchClick: OnClickLastSearch
) : RecyclerView.Adapter<RecyclerSearchViewHolder>() {

    var mSearches = searches
    var mDeleteClick = deleteClick
    var mSearchClick = searchClick

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerSearchViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.search_item, parent, false)
        return RecyclerSearchViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerSearchViewHolder, position: Int) {
        holder.render(this.mSearches[position], this.mDeleteClick, this.mSearchClick)
    }

    fun updateList(searches: MutableList<SearchModel>){
        this.mSearches = searches
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return this.mSearches.size
    }
}