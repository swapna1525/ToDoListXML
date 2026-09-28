package com.example.todolistxml

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var taskAdapter: TaskAdapter

    private val taskList = mutableListOf<TaskModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        recyclerView = findViewById(R.id.recyclerView)
        taskAdapter = TaskAdapter(
            taskList,

            onDeleteClick = { position ->
                taskList.removeAt(position)
                taskAdapter.notifyItemRemoved(position)
            },

            onTaskChecked = { position, isChecked ->

                val task = taskList[position]
                task.isCompleted = isChecked

                taskList.removeAt(position)

                if (isChecked) {
                    // Move completed task to the bottom
                    taskList.add(taskList.size, task)
                } else {
                    // Move uncompleted task to the top
                    taskList.add(0, task)
                }

                taskAdapter.notifyDataSetChanged()
            }
        )
        recyclerView.adapter = taskAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        val etTask = findViewById<EditText>(R.id.etTask)
        val btnAdd = findViewById<Button>(R.id.btnAdd)

        btnAdd.setOnClickListener {
            val task = etTask.text.toString()

            if (task.isNotEmpty()) {

                taskList.add(TaskModel(task))
                taskAdapter.notifyItemInserted(taskList.size - 1)

                etTask.text.clear()
                Toast.makeText(
                    this,
                    "Task added successfully $task",
                    Toast.LENGTH_SHORT
                ).show()

            } else {
                Toast.makeText(
                    this,
                    "Please enter a task",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}