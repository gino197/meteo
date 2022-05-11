package com.zebutech.meteo.utils;
import android.icu.util.Calendar;
import java.util.Date;
import android.icu.text.SimpleDateFormat;
import java.util.Locale;

/*DATE MAINTENANT*/
public class DateNow
{  
   private Date c;
   public DateNow(){
       this.c = Calendar.getInstance().getTime();      
   }
   public String getDate(){
       SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
       String formattedDate = df.format(c);
       return formattedDate;
   }
   public String getHeure(){
       SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
       String formattedDate = df.format(c);
       return formattedDate;
   }
   public String setFormat(String dateStr){

       SimpleDateFormat curFormater = new SimpleDateFormat("yyyy-MM-dd");
       Date dateObj = new Date();
       try{
           dateObj = curFormater.parse(dateStr);  
       }
       catch(Exception e){
           
       }
       SimpleDateFormat postFormater = new SimpleDateFormat("dd MMM yyyy"); 

       String newDateStr = postFormater.format(dateObj);
       return newDateStr;
   }
}
