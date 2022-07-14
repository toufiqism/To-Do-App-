package com.tvl.tvltodolist.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.tvl.tvltodolist.R
import com.tvl.tvltodolist.model.Notes


class TestAdapter(
    private val click: (Notes) -> Unit,
) :
    RecyclerView.Adapter<RclViewHolder>() {


    var subscriberList = ArrayList<Notes>()

    fun setList(subscribers: List<Notes>) {
        subscriberList.clear()
        subscriberList.addAll(subscribers)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RclViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val listItem = layoutInflater.inflate(R.layout.list_item, parent, false)
        return RclViewHolder(listItem)
    }

    override fun onBindViewHolder(holder: RclViewHolder, position: Int) {
        holder.bind(subscriberList[position], click)
    }

    override fun getItemCount(): Int {
        return subscriberList.size
    }
}

class RclViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private lateinit var textViewName: TextView
    private lateinit var textViewEmail: TextView
    lateinit var ids: View

    fun bind(subscriber: Notes, click: (Notes) -> Unit) {
        textViewName = itemView.findViewById(R.id.name)
        textViewEmail = itemView.findViewById(R.id.email)
        ids = itemView.findViewById(R.id.ids)
        textViewName.text = subscriber.title
        textViewEmail.text = subscriber.notes
        ids.setOnClickListener {
            click(subscriber)
        }
    }
}