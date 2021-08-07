package com.mihir.meralist.data

import android.text.Spannable
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
@TypeConverters(SpannableToString::class)
@Entity(tableName = "notes_table")
data class Notes(
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    var title: String,
    var text:Spannable)