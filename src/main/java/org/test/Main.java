package org.test;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Taras on 24.11.2016.
 */
public class Main {
    public static void main(String[] arg){
        Calendar cal = Calendar.getInstance();
        final DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date currentDate = new Date(dateFormat.format(cal.getTime()));
        System.out.println(currentDate);
        Timestamp sqlDate = (new Timestamp(currentDate.getTime()));
        System.out.println(sqlDate);
        System.out.println(new Date(sqlDate.getTime()));

    }
}
