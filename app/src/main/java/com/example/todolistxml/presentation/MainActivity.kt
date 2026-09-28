package com.example.todolistxml.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todolistxml.viewmodel.TaskViewModel
import com.example.todolistxml.data.TaskModel
import com.example.todolistxml.databinding.ActivityMainBinding
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var taskAdapter: TaskAdapter
    private val taskViewModel: TaskViewModel by viewModels()

    private val taskList = mutableListOf<TaskModel>()
    private lateinit var binding: ActivityMainBinding


    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        recyclerView = binding.recyclerView
        taskAdapter = TaskAdapter(
            taskList,

            onDeleteClick = { position ->
                val task = taskList[position]

                taskViewModel.deleteTask(task)

                taskList.removeAt(position)
                taskAdapter.notifyItemRemoved(position)
            },

            onTaskChecked = { position, isChecked ->

                val task = taskList[position]

                task.isCompleted = isChecked

                // Save the updated completion status to Room
                taskViewModel.updateTask(task)

                taskList.removeAt(position)

                if (isChecked) {
                    // Move completed task to the bottom
                    taskList.add(taskList.size, task)
                } else {
                    // Move uncompleted task to the top
                    taskList.add(0, task)
                }

                taskAdapter.notifyDataSetChanged()
            })
        recyclerView.adapter = taskAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                taskViewModel.allTasks.collect { savedTasks ->
                    taskList.clear()
                    taskList.addAll(savedTasks)
                    taskAdapter.notifyDataSetChanged()
                }
            }
        }

        val etTask = binding.etTask
        val btnAdd = binding.btnAdd

        btnAdd.setOnClickListener {
            val task = etTask.text.toString()

            if (task.isNotEmpty()) {

                val newTask = TaskModel(title = task)

                taskList.add(0, newTask)
                taskAdapter.notifyItemInserted(0)

                taskViewModel.addTask(newTask)

                etTask.text.clear()

                Toast.makeText(
                    this, "Task added successfully $task", Toast.LENGTH_SHORT
                ).show()

            } else {
                Toast.makeText(
                    this, "Please enter a task", Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}