package com.loan555.roomdemo.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

/**
 * Lop entity de tao ra bang
 */
@Entity(tableName = "note_table")
class Note(
    @ColumnInfo(name = "title_col") var title: String = "",
    @ColumnInfo(name = "description_col") var description: String = "",
    @ColumnInfo(name = "imgPath_col") var imgPath: String = ""
) : Serializable {
    @PrimaryKey(autoGenerate = true)//tu dong khoi tao
    @ColumnInfo(name = "note_id_col")
    var id: Int = 0
}