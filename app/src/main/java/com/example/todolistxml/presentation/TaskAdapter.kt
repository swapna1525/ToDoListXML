package com.example.todolistxml.presentation

import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.todolistxml.R
import com.example.todolistxml.data.TaskModel
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class TaskAdapter(
    private val taskList: MutableList<TaskModel>,
    private val onDeleteClick: (Int) -> Unit,
    private val onTaskChecked: (Int, Boolean) -> Unit

) : RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val checkBoxTask: CheckBox =
            itemView.findViewById(R.id.checkBoxTask)

        val btnDelete: ImageView =
            itemView.findViewById(R.id.btnDelete)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TaskViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_task, parent, false)

        return TaskViewHolder(view)
    }



    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(
        holder: TaskViewHolder,
        position: Int
    ) {
        val task = taskList[position]

        holder.itemView.findViewById<TextView>(R.id.tvTaskName).text = task.title

        holder.checkBoxTask.setOnCheckedChangeListener(null)
        holder.checkBoxTask.isChecked = task.isCompleted

        holder.checkBoxTask.setOnCheckedChangeListener { _, isChecked ->
            task.isCompleted = isChecked
        }
        holder.checkBoxTask.setOnCheckedChangeListener { _, isChecked ->
            onTaskChecked(position, isChecked)
        }
        val currentDateTime = LocalDateTime.now()

        val formatter = DateTimeFormatter.ofPattern("dd MMM yyyy, hh:mm a")

        val formattedDateTime = currentDateTime.format(formatter)

        holder.itemView.findViewById<TextView>(R.id.tvCreationDate).text = formattedDateTime
        holder.btnDelete.setOnClickListener {
            val currentPosition = holder.bindingAdapterPosition

            if (currentPosition != RecyclerView.NO_POSITION) {
                onDeleteClick(currentPosition)
            }
        }
    }

    override fun getItemCount(): Int {
        return taskList.size
    }
}