package com.loan555.roomdemo.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.loan555.roomdemo.R
import com.loan555.roomdemo.model.Note
import com.loan555.roomdemo.ui.viewmodel.NoteViewModel
import kotlinx.android.synthetic.main.activity_update.*

class UpdateActivity : AppCompatActivity() {
    private val noteViewModel: NoteViewModel by lazy {
        ViewModelProvider(
            this,
            NoteViewModel.NoteViewModelFactory(this.application)
        )[NoteViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update)
        val note = intent.getSerializableExtra("UPDATE_NOTE") as Note
        edit_note_title.setText(note.title)
        edit_note_des.setText(note.description)
        btnUpdateFragment.setOnClickListener {
            note.title = edit_note_title.text.toString()
            note.description = edit_note_des.text.toString()
            noteViewModel.updateNote(note)
            finish()
        }
    }
}