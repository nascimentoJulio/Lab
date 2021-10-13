package com.example.study.ui

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.study.R
import com.example.study.constants.Constants
import com.example.study.ui.viewmodel.UpdateViewModel
import java.util.*

class UpdateTaskActivity : AppCompatActivity(), DatePickerDialog.OnDateSetListener {

    lateinit var mDueDate: String
    lateinit var mDueDateSelector: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_task)

        val title = findViewById<EditText>(R.id.edit_update_title)
        val description = findViewById<EditText>(R.id.edit_update_description)
        val topic = findViewById<EditText>(R.id.edit_update_topic)
        this.mDueDateSelector = findViewById(R.id.button_update_show_date_picker)
        val buttonSave = findViewById<Button>(R.id.button_update_save)
        val checkCompleted = findViewById<CheckBox>(R.id.check_update_is_complete)

        val mViewModel = UpdateViewModel(this)
        val bundle = intent.extras
        val id = bundle?.getLong(Constants.BUNDLE)
        val taskModel = id?.let { mViewModel.getTaskById(it).value }

        this.mDueDate = taskModel?.dueDate.toString()

        title.setText(taskModel?.title)
        description.setText(taskModel?.description)
        topic.setText(taskModel?.topic)
        mDueDateSelector.text = this.mDueDate
        checkCompleted.isChecked = taskModel?.isCompleted!!

        mDueDateSelector.setOnClickListener {
            showDatePicker()
        }

        buttonSave.setOnClickListener {
            if (title.text.toString().isEmpty()
                || description.text.toString().isEmpty()
                || topic.text.toString().isEmpty()
                || this.mDueDate.isEmpty()
            ) {
                Toast.makeText(this, R.string.empty_fields, Toast.LENGTH_SHORT).show()
            } else {
                taskModel.title = title.text.toString()
                taskModel.description = description.text.toString()
                taskModel.topic = topic.text.toString()
                taskModel.dueDate = this.mDueDate
                taskModel.isCompleted = checkCompleted.isChecked
                taskModel.let { it1 -> mViewModel.update(it1) }
                startActivity(
                    Intent(this, MainActivity::class.java)
                )
                Toast.makeText(this, R.string.update_message, Toast.LENGTH_LONG).show()
            }
        }
    }

    fun showDatePicker() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        DatePickerDialog(this, this, year, month, day).show()
    }

    override fun onDateSet(p0: DatePicker?, year: Int, month: Int, day: Int) {
        if (month + 1 < 10)
            this.mDueDate = String.format("%d/0%d/%d", day, month + 1, year)
        else
            this.mDueDate = String.format("%d/%d/%d", day, month + 1, year)
        this.mDueDateSelector.text = this.mDueDate
    }
}