package com.example.study.ui

import android.content.Context
import android.content.Intent
import android.graphics.Canvas
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.study.R
import com.example.study.constants.Constants
import com.example.study.listeners.OnSeeDetailsListener
import com.example.study.models.TaskModel
import com.example.study.ui.recycler.RecyclerTaskAdapter
import com.example.study.ui.viewmodel.MainViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity(), OnSeeDetailsListener {

    lateinit var mAdapter: RecyclerTaskAdapter
    lateinit var mViewModel: MainViewModel
    lateinit var mTextEmptyRecycler: TextView
    lateinit var mRecycler: RecyclerView
    val mContext = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.mRecycler = findViewById<RecyclerView>(R.id.recycler_tasks)
        val addButton = findViewById<FloatingActionButton>(R.id.add_button)
        val buttonSearch = findViewById<Button>(R.id.button_main_search)
        this.mTextEmptyRecycler = findViewById(R.id.text_empty_recycler)


        this.mViewModel = MainViewModel(this)

        this.mAdapter = RecyclerTaskAdapter(arrayListOf(), this)
        this.mRecycler.adapter = this.mAdapter
        this.mRecycler.layoutManager = LinearLayoutManager(this)

        val handleSwipe = object : SwipeDelete(this){
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

        if (this.mAdapter.itemCount == 0){
            this.mRecycler.visibility = View.INVISIBLE
            this.mTextEmptyRecycler.visibility = View.VISIBLE
        }

        val touchHelper = ItemTouchHelper(handleSwipe)
        touchHelper.attachToRecyclerView(this.mRecycler)

        addButton.setOnClickListener {
            startActivity(
                Intent(this, RegisterTaskActivity::class.java)
            )
        }

        buttonSearch.setOnClickListener {
            startActivity(
                Intent(this, SearchActivity::class.java)
            )
        }

        this.loadObservers()
    }

    private fun loadObservers() {
        this.mViewModel.getAllTasks().observe(this, {
            this.mAdapter.updateUI(it)
        })
    }

    override fun onResume() {
        super.onResume()
        this.loadObservers()
        if (this.mAdapter.itemCount != 0){
            this.mRecycler.visibility = View.VISIBLE
            this.mTextEmptyRecycler.visibility = View.INVISIBLE
        }
        else{
            this.mRecycler.visibility = View.INVISIBLE
            this.mTextEmptyRecycler.visibility = View.VISIBLE
        }

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

abstract class SwipeDelete(context: Context):
    ItemTouchHelper.SimpleCallback (0, ItemTouchHelper.RIGHT){
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