package com.loan555.roomdemo.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.loan555.roomdemo.database.dao.NoteDao
import com.loan555.roomdemo.model.Note

@Database(entities = [Note::class], version = 1)//danh sach nhung class entity da tao
abstract class NoteDatabase : RoomDatabase() {
    abstract fun getNoteDao(): NoteDao

    companion object {
        @Volatile // chu thich chay tren JVM
        private var instance: NoteDatabase? = null

        fun getInstance(context: Context): NoteDatabase {
            if (instance == null) {
                instance =
                    Room.databaseBuilder(context, NoteDatabase::class.java, "NoteDatabase").build()
            }
            return instance!!
        }
    }
}