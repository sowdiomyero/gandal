package models.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: DYSOW
 * Date: 19/06/15
 * Time: 19:16
 * To change this template use File | Settings | File Templates.
 */
public class DateUtils {

    private static final String CURRENT_DATE_FORMAT = "yyyy-MM-dd";

    public static String getPreviousDateString(int nbDaypast) {

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, nbDaypast);
        DateFormat dateFormat = new SimpleDateFormat(CURRENT_DATE_FORMAT);
        return dateFormat.format(cal.getTime());
    }
    
    private static String getDayFromDate(String date) {

        Calendar cal = Calendar.getInstance();
        cal.setTime(DateUtils.getPreviousDateString(date));
        int day = cal.get(Calendar.DAY_OF_WEEK);
        //Logger.info("gatDayFromDate : "+date+" ==> "+day);
        //switch (day){
        if(day == 2)
                return "Lundi";
        if(day == 3)
                return "Mardi";
        if(day == 4)
                return "Mercredi";
        if(day == 5)
                return "Jeudi";
        if(day == 6)
                return "Vendredi";
        if(day == 7)
                return "Samedi";
        if(day == 1)
                return "Dimanche";

        return  "";
    }
    
    public static Date getPreviousDateString(String date) {
        DateFormat dateFormat = new SimpleDateFormat(CURRENT_DATE_FORMAT);
        Date dd = null;
        try {
            dd= dateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();  
        }
        return dd;
    }

    public static String getFormatDateString(Date date, String format) {
        if(format ==null)
            format =CURRENT_DATE_FORMAT;
        DateFormat dateFormat = new SimpleDateFormat(format);
        String value ="";
        try {
            value= dateFormat.format(date);
        } catch (Exception e) {
            e.printStackTrace(); 
        }
        return value;
    }

   /* public static Date getPreviousDate(int nbDaypast) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, nbDaypast);

        DateFormat dateFormat = new SimpleDateFormat(CURRENT_DATE_FORMAT);
        return dateFormat.format(cal.getTime());
       // return cal.getTime();
    }*/
}
