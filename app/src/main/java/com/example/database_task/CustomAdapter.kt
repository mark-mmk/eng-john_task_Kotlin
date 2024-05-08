package com.example.database_task

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CustomAdapter(var list: ArrayList<data>, val dbHelper: MyDatabaseHelper) :
    RecyclerView.Adapter<CustomAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val id: TextView = itemView.findViewById(R.id.id)
        val title: TextView = itemView.findViewById(R.id.title)
        val body: TextView = itemView.findViewById(R.id.body)
        val isdone: TextView = itemView.findViewById(R.id.done)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomAdapter.ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.shape, parent, false)
        )
    }

    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: CustomAdapter.ViewHolder, position: Int) {
        holder.title.text = "Title : " + list[position].Title
        holder.id.text = "ID : " + list[position].ID.toString()
        holder.body.text = "Body : " + list[position].Body
        holder.isdone.text = "isDone : " + list[position].isDone.toString()
        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, Information::class.java)
            intent.putExtra("ID", list[position].ID)
            intent.putExtra("Title", list[position].Title)
            intent.putExtra("Body", list[position].Body)
            intent.putExtra("isDone", list[position].isDone)
            intent.putExtra("index", 0)
            holder.itemView.context.startActivity(intent)
        }
        holder.itemView.setOnLongClickListener {
            dbHelper.deleteTodo(list[position].ID)
            list.removeAt(position)
            notifyItemRemoved(position)
            true
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}
