package com.loan555.roomdemo.api

import com.loan555.roomdemo.model.Note
import retrofit2.Call
import retrofit2.http.GET

interface NoteClient {
    @GET("/note")
    fun getAllNote():Call<List<Note>>
}