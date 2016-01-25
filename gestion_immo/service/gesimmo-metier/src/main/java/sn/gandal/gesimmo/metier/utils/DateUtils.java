/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sn.gandal.gesimmo.metier.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sniang
 */
public class DateUtils {
    
    public static Date getMonthAgo() {
        Date dateMois = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(dateMois);
        cal.add(Calendar.MONTH, -1);
        dateMois = cal.getTime();
        return dateMois;
    }

    public static Date getWeekAgo() {
        Date dateSemainePassee = new Date(System.currentTimeMillis() - 7L * 24 * 3600 * 1000);
        return dateSemainePassee;
    }

    public static Date getDateToday(){
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.FRENCH);
        Date dateDuJour = new Date();
        
        String dateToday = formatter.format(dateDuJour);
        Date dateTodayCorrect;
        try {
            dateTodayCorrect = formatter.parse(dateToday);
        } catch (ParseException ex) {
            Logger.getLogger(DateUtils.class.getName()).log(Level.SEVERE, null, ex);
            dateTodayCorrect= new Date();
        }
        return dateTodayCorrect;
    }
    
}
