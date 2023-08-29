package com.saadahmedsoft.sparkconvo.view.dashboard.tabs.chat.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.saadahmedsoft.sparkconvo.R
import com.saadahmedsoft.sparkconvo.helper.log
import com.saadahmedsoft.sparkconvo.service.dto.conversation.Chats
import java.text.SimpleDateFormat
import java.util.Locale

class ChatAdapter(private val receiverId: Long) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val SENDER = 1
        private const val RECEIVER = 2
    }

    private val simpleDateFormat = SimpleDateFormat("dd-MM-yyyy'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
    private val formatter = SimpleDateFormat("h:mm a", Locale.getDefault())
    private var items = mutableListOf<Chats>()

    @SuppressLint("NotifyDataSetChanged")
    fun addItems(items: List<Chats>) {
        this.items = items as MutableList<Chats>
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun addItem(item: Chats) {
        this.items.add(item)
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun removeItem(item: Chats) {
        this.items.remove(item)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        var viewHolder: RecyclerView.ViewHolder? = null

        when(viewType) {
            SENDER -> {
                viewHolder = RightViewHolder(inflater.inflate(R.layout.item_layout_chat_right, parent, false))
            }
            RECEIVER -> {
                viewHolder = RightViewHolder(inflater.inflate(R.layout.item_layout_chat_left, parent, false))
            }
        }

        return viewHolder!!
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = items[position]
        val date = simpleDateFormat.parse(item.createdAt!!)
        val time = formatter.format(date!!)

        if (holder is LeftViewHolder) {
            holder.tvMessage.text = item.message
            holder.time.text = time
        }
        else if (holder is RightViewHolder) {
            holder.tvMessage.text = item.message
            holder.time.text = time
        }
    }

    override fun getItemViewType(position: Int): Int {
        if (items[position].senderId != receiverId) {
            return SENDER
        }

        return RECEIVER
    }

    private class LeftViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvMessage: TextView = itemView.findViewById(R.id.tv_message)
        val time = itemView.findViewById<TextView>(R.id.tv_time)
    }

    private class RightViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvMessage: TextView = itemView.findViewById(R.id.tv_message)
        val time = itemView.findViewById<TextView>(R.id.tv_time)
    }
}