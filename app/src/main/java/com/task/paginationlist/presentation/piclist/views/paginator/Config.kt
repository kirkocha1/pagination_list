package com.task.paginationlist.presentation.piclist.views.paginator

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Locale


object Config {
    val LIMIT = 20
    val GRID_ROWS_COUNT = 2
    val INPUT_DATA = "yyyy-MM-dd'T'hh:mm:ss.SSS'Z'"
    val OUTPUT_DATA = "yyyy-MM-dd"
    val FORMAT_DATE_OUTPUT: ThreadLocal<SimpleDateFormat> = object : ThreadLocal<SimpleDateFormat>() {
        override fun initialValue(): SimpleDateFormat {
            return SimpleDateFormat(OUTPUT_DATA, Locale.getDefault())
        }
    }
    val FORMAT_DATE_INPUT: ThreadLocal<SimpleDateFormat> = object : ThreadLocal<SimpleDateFormat>() {
        override fun initialValue(): SimpleDateFormat {
            return SimpleDateFormat(INPUT_DATA, Locale.getDefault())
        }
    }

    @Throws(ParseException::class)
    fun formatDate(date: String): String {
        return FORMAT_DATE_OUTPUT.get().format(FORMAT_DATE_INPUT.get().parse(date))
    }
}
