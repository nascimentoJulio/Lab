package com.example.study.ui

import android.content.Context
import android.content.Intent
import android.graphics.Canvas
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.study.R
import com.example.study.constants.Constants
import com.example.study.listeners.OnSeeDetailsListener
import com.example.study.ui.recycler.RecyclerTaskAdapter
import com.example.study.ui.viewmodel.ResultSearchViewModel

class ResultSearchActivity : AppCompatActivity(), OnSeeDetailsListener {

    lateinit var mViewModel: ResultSearchViewModel
    lateinit var mAdapter: RecyclerTaskAdapter
    val mContext = this
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result_search)

        val recycler = findViewById<RecyclerView>(R.id.recycler_founded_itens)
        val textCounter = findViewById<TextView>(R.id.text_founded_itens)

        this.mViewModel = ResultSearchViewModel(this)
        this.mAdapter = RecyclerTaskAdapter(arrayListOf(), this)
        recycler.adapter = this.mAdapter
        recycler.layoutManager = LinearLayoutManager(this)

        val bundle = intent.extras
        val searchWord = bundle?.getString(Constants.BUNDLE)
        searchWord?.let {
            this.mViewModel.getTasksByName("$searchWord%").observe(this, { tasks ->
                this.mAdapter.updateUI(tasks)
                textCounter.text = "${tasks.size} tasks were found with the title $searchWord"
            })
        }

        val handleSwipe = object : SwipeDelete(this) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val task = mAdapter.getItemByPosition(viewHolder.adapterPosition)
                mViewModel.delete(task)
                mAdapter.updateRemovedItem(viewHolder.adapterPosition)
                Toast.makeText(mContext, R.string.delete_message, Toast.LENGTH_LONG).show()
            }

        }

        val touchHelper = ItemTouchHelper(handleSwipe)
        touchHelper.attachToRecyclerView(recycler)


    }


    override fun onClick(id: Long) {
        startActivity(
            Intent(this, DetailsActivity::class.java).apply {
                val bundle = Bundle()
                bundle.putLong(Constants.BUNDLE, id)
                putExtras(bundle)
            }
        )
    }
}


abstract class SwipeDeleteSearch(context: Context) :
    ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
    override fun onChildDraw(
        c: Canvas,
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        dX: Float,
        dY: Float,
        actionState: Int,
        isCurrentlyActive: Boolean
    ) {


        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)

    }
}