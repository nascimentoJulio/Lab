package com.example.study.ui

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.Adapter
import android.widget.EditText
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.study.R
import com.example.study.constants.Constants
import com.example.study.listeners.OnClickLastSearch
import com.example.study.listeners.OnDeleteSearch
import com.example.study.models.SearchModel
import com.example.study.ui.recycler.RecyclerSearchAdapter
import com.example.study.ui.viewmodel.SearchViewModel
import java.sql.Timestamp
import java.time.LocalDate

class SearchActivity : AppCompatActivity(), OnDeleteSearch, OnClickLastSearch {

    lateinit var mViewModel: SearchViewModel
    lateinit var mAdapter: RecyclerSearchAdapter

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        this.mViewModel = SearchViewModel(this)
        val editSearch = findViewById<EditText>(R.id.edit_search_screen)
        val recycler = findViewById<RecyclerView>(R.id.recycler_searches)


        this.mAdapter = RecyclerSearchAdapter(arrayListOf(),this,this)

        recycler.adapter = this.mAdapter
        recycler.layoutManager = LinearLayoutManager(this)

        editSearch.setOnEditorActionListener { _, actionId, _ ->
            when (actionId) {
                EditorInfo.IME_ACTION_SEARCH -> {
                    editSearch.clearFocus()

                    val input: InputMethodManager =
                        getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    input.hideSoftInputFromWindow(editSearch.windowToken, 0)

                    this.saveSearch(editSearch.text.toString())
                    this.startResultActivity(editSearch.text.toString())
                    true
                }
                else -> false
            }
        }

        this.loadObservers()
    }
    private fun loadObservers(){
        this.mViewModel.getSearches().observe(this,{
            mAdapter.updateList(it)
        })
    }

    override fun onResume() {
        super.onResume()
        this.loadObservers()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun saveSearch(title: String){
        val searchModel = SearchModel(
            id = 0,
            title = title,
            date = System.currentTimeMillis()
        )
        this.mViewModel.insert(searchModel)
    }

    private fun startResultActivity(searchWord: String){
        startActivity(
            Intent(this, ResultSearchActivity::class.java).apply {
                val bundle = Bundle()
                bundle.putString(Constants.BUNDLE, searchWord)
                putExtras(bundle)
            }
        )
    }

    override fun onClick(searchModel: SearchModel) {
        this.mViewModel.delete(searchModel)
        this.loadObservers()
    }

    override fun onLastClick(search: String) {
        this.startResultActivity(search)
    }


}