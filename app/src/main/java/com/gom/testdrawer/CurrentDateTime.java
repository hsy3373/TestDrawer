package com.gom.testdrawer;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class CurrentDateTime {
    public static String getCurrentDateTime() {
        long dateValue  = System.currentTimeMillis();
        Date date = new Date(dateValue);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm", Locale.getDefault());
        String currentDateTime =  simpleDateFormat.format(date) ;

        return currentDateTime;
    }
}
