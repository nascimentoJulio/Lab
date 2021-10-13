package com.example.study.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.study.R
import com.example.study.constants.Constants
import com.example.study.ui.viewmodel.DetailsViewModel

class DetailsActivity : AppCompatActivity() {
    lateinit var mViewModel: DetailsViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        val title = findViewById<TextView>(R.id.text_title_detail)
        val description = findViewById<TextView>(R.id.text_description_detail)
        val topic = findViewById<TextView>(R.id.text_topic_detail)
        val dueDate = findViewById<TextView>(R.id.text_due_date_detail)
        val buttonDelete = findViewById<Button>(R.id.button_delete)
        val buttonUpdate = findViewById<Button>(R.id.button_update)
        this.mViewModel = DetailsViewModel(this)

        val bundle = intent.extras
        val id = bundle?.getLong(Constants.BUNDLE)
        val taskModel = id?.let { this.mViewModel.getTaskById(it).value }

        buttonDelete.setOnClickListener {
            taskModel?.let { this.mViewModel.delete(it) }
            onBackPressed()
        }

        buttonUpdate.setOnClickListener {
            startActivity(
                Intent(this, UpdateTaskActivity::class.java).apply {
                    val bundle = Bundle()
                    id?.let { it1 -> bundle.putLong(Constants.BUNDLE, it1) }
                    putExtras(bundle)
                }
            )
        }

        title.text = taskModel?.title
        description.text = taskModel?.description
        topic.text = taskModel?.topic
        dueDate.text = taskModel?.dueDate
    }


}