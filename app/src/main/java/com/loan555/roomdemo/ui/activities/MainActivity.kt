package com.loan555.roomdemo.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.loan555.roomdemo.R
import com.loan555.roomdemo.api.ApiConfig
import com.loan555.roomdemo.api.NoteClient
import com.loan555.roomdemo.ui.adapter.NoteAdapter
import com.loan555.roomdemo.model.Note
import com.loan555.roomdemo.ui.viewmodel.NoteViewModel
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
//main ??
class MainActivity : AppCompatActivity() {
    private val noteViewModel: NoteViewModel by lazy {
        ViewModelProvider(
            this,
            NoteViewModel.NoteViewModelFactory(this.application)
        )[NoteViewModel::class.java]
    }

    private val client: NoteClient = ApiConfig.retrofit.create(NoteClient::class.java)
    private lateinit var service: Call<List<Note>>
    private lateinit var adapter: NoteAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initControl()
        initEvent()
    }

    private fun initEvent() {
        buttonAdd.setOnClickListener {
            val intent = Intent(this, AddNoteActivity::class.java)
            startActivity(intent)
        }
    }

    private fun initControl() {
        adapter = NoteAdapter(this, onItemClick, onItemDelete)
        recycleView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recycleView.adapter = adapter
//        noteViewModel.getAllNote().observe(this, {
//            adapter.setNotes(it)
//        })
        fetchData()
    }

    private fun fetchData() {
        service = client.getAllNote()
        Log.d("aaa", "fetchData")
        service.enqueue(object : Callback<List<Note>> {
            override fun onResponse(call: Call<List<Note>>?, response: Response<List<Note>>) {
                Toast.makeText(this@MainActivity, response.code().toString(), Toast.LENGTH_SHORT)
                    .show()
                Log.d("aaa", "response?.code() = ${response.code()}")
                if (response.isSuccessful) {
                    val list = response.body()
                    adapter.setNotes(list!!)
                }
            }

            override fun onFailure(call: Call<List<Note>>?, t: Throwable?) {
                Toast.makeText(this@MainActivity, t.toString(), Toast.LENGTH_SHORT).show()
                Log.d("aaa", "onFailure = $t")
            }
        })
    }

    private val onItemClick: (Note) -> Unit = {//ham call back
        val intent = Intent(this, UpdateActivity::class.java)
        intent.putExtra("UPDATE_NOTE", it)
        startActivity(intent)
    }

    private val onItemDelete: (Note) -> Unit = {
        noteViewModel.deleteNote(it)
    }
}