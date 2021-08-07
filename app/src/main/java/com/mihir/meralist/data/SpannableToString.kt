package com.mihir.meralist.data

import android.text.Spannable
import androidx.core.text.toSpannable
import androidx.room.TypeConverter

class SpannableToString {

    @TypeConverter
    fun SpanToString(Spannable:Spannable?): String? {
        return Spannable?.toString()
    }
    @TypeConverter
    fun StringToSpannable(string:String?): Spannable? {
        return string?.toSpannable()

    }
}