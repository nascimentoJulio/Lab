package com.example.study.ui.recycler

import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.study.R
import com.example.study.listeners.OnClickLastSearch
import com.example.study.listeners.OnDeleteSearch
import com.example.study.models.SearchModel

class RecyclerSearchViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val title: TextView = itemView.findViewById(R.id.text_title_search)
    private val container: ConstraintLayout = itemView.findViewById(R.id.id_search_item_container)
    private val buttonDelete: ImageButton = itemView.findViewById(R.id.button_delete_search)

    fun render(searchModel: SearchModel, deleteClick: OnDeleteSearch, searchClick: OnClickLastSearch){
        title.text = searchModel.title
        this.buttonDelete.setOnClickListener {
            deleteClick.onClick(searchModel)
        }

        this.container.setOnClickListener {
            searchClick.onLastClick(searchModel.title)
        }
    }
}
