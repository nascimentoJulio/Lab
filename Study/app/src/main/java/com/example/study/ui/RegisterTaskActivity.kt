package com.example.study.ui

import android.app.DatePickerDialog
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.DatePicker
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.study.R
import com.example.study.models.TaskModel
import com.example.study.ui.viewmodel.RegisterViewModel
import com.google.android.material.textfield.TextInputEditText
import java.util.*

class RegisterTaskActivity : AppCompatActivity(), DatePickerDialog.OnDateSetListener {

    lateinit var mDueDate: String
    lateinit var dueDateSelector: Button

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_task)
        val title = findViewById<TextInputEditText>(R.id.edit_title)
        val description = findViewById<TextInputEditText>(R.id.edit_description)
        val topic = findViewById<TextInputEditText>(R.id.edit_topic)
        this.dueDateSelector = findViewById(R.id.show_date_picker)
        val buttonCreate = findViewById<Button>(R.id.button_create)
        val isCompleted = findViewById<CheckBox>(R.id.check_is_complete)

        this.mDueDate = ""
        val mViewModel = RegisterViewModel(this)

        dueDateSelector.setOnClickListener {
            this.showDatePicker()
        }


        buttonCreate.setOnClickListener {
            if (title.text.toString().isEmpty()
                || description.text.toString().isEmpty()
                || topic.text.toString().isEmpty()
                || this.mDueDate.isEmpty()
            ) {
                Toast.makeText(this, R.string.empty_fields,Toast.LENGTH_SHORT).show()
            } else {
                val task = TaskModel(
                    id = 0,
                    title = title.text.toString(),
                    description = description.text.toString(),
                    topic = topic.text.toString(),
                    dueDate = this.mDueDate,
                    isCompleted = isCompleted.isChecked
                )
                mViewModel.insert(task)
                onBackPressed()
                Toast.makeText(this, R.string.insert_message, Toast.LENGTH_LONG).show()
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
        this.dueDateSelector.text = this.mDueDate

    }

}