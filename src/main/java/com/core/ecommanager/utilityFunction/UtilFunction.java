package com.core.ecommanager.utilityFunction;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UtilFunction {
    public static Date convertStrToDateTime(String strDateTime){
        DateFormat dateFormat = new SimpleDateFormat("yyy-MM-dd HH:mm:ss");
        Date date = null;
        try{
            date =dateFormat.parse(strDateTime);
        }catch (ParseException pe){
            // log here
            System.out.println("Exception during converting from string to date");
            pe.printStackTrace();
        }
        return date;
    }
}
