package com.dariuszpaluch.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateValid {
    public static boolean dateValid(String date) {
        try {
            DateFormat format = new SimpleDateFormat("d-M-yyyy", Locale.ENGLISH);
            Date resultDate = format.parse(date);

            if(resultDate.before(new Date())) {
                return true;
            }
        } catch (Exception e) {
            return false;
        }

        return false;
    }
}
