package com.task.paginationlist.presentation.piclist.views.paginator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;


public class Config {
    public static final int LIMIT = 20;
    public static final int GRID_ROWS_COUNT = 2;
    public static final String INPUT_DATA = "yyyy-MM-dd'T'hh:mm:ss.SSS'Z'";
    public static final String OUTPUT_DATA = "yyyy-MM-dd";
    public static final ThreadLocal<SimpleDateFormat> FORMAT_DATE_OUTPUT = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat(OUTPUT_DATA, Locale.getDefault());
        }
    };
    public static final ThreadLocal<SimpleDateFormat> FORMAT_DATE_INPUT = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat(INPUT_DATA, Locale.getDefault());
        }
    };

    public static final String formatDate(String date) throws ParseException{
        return FORMAT_DATE_OUTPUT.get().format(FORMAT_DATE_INPUT.get().parse(date));
    }
}
