package com.loan555.roomdemo.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.loan555.roomdemo.R
import com.loan555.roomdemo.model.Note

class NoteAdapter(
    private val context: Context,
    private val onClick: (Note) -> Unit,
    private val onDelete: (Note) -> Unit
) :
    RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {
    private var notes: List<Note> = listOf()

    inner class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val txtTitle: TextView = itemView.findViewById(R.id.textView)
        private val txtDes: TextView = itemView.findViewById(R.id.textView2)
        private val btnDelete: ImageButton = itemView.findViewById(R.id.btnDel)
        private val layoutItem: LinearLayout = itemView.findViewById(R.id.layoutItem)
        private val imgNote: ImageView = itemView.findViewById(R.id.imgNote)
        fun onBind(note: Note) {
            txtDes.text = note.description
            txtTitle.text = note.title
            Glide.with(context).load(note.imgPath).into(imgNote)
            btnDelete.setOnClickListener { onDelete(note) }
            layoutItem.setOnClickListener { onClick(note) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val itemView =
            LayoutInflater.from(context).inflate(R.layout.item_view_note_adapter, parent, false)
        return NoteViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.onBind(notes[position])
    }

    override fun getItemCount(): Int = notes.size

    fun setNotes(notes: List<Note>) {
        this.notes = notes
        notifyDataSetChanged()
    }
}