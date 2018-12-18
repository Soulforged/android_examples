package io.personal.dicardo.notes

import android.graphics.drawable.ColorDrawable
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlin.math.absoluteValue

class NotesAdapter(private val myDataset: Array<Note>, private val onItemClickListener: View.OnClickListener) :
    RecyclerView.Adapter<NotesAdapter.MyViewHolder>() {

    class MyViewHolder(val textView: TextView) : RecyclerView.ViewHolder(textView)


    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): NotesAdapter.MyViewHolder {
        val textView = LayoutInflater.from(parent.context)
            .inflate(android.R.layout.simple_list_item_1, parent, false) as TextView
        textView.setOnClickListener(onItemClickListener)
        return MyViewHolder(textView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        var bgColor = if (position % 2 == 0) android.R.color.holo_red_light else android.R.color.holo_blue_light
        val textView = holder.textView
        textView.setBackgroundColor(bgColor.absoluteValue)
        textView.text = myDataset[position].text
        textView.tag = position
    }

    override fun getItemCount() = myDataset.size
}