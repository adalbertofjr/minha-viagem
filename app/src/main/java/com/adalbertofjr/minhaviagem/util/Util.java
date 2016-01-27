package com.adalbertofjr.minhaviagem.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by AdalbertoF on 27/01/2016.
 */
public class Util {

    public static Date criarData(int anoSelecionado, int mesSelecionado, int diaSelecionado) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(anoSelecionado, mesSelecionado, diaSelecionado);
        return calendar.getTime();
    }

    public static String dateToStringFormat(Date date){
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return dateFormat.format(date);
    }

    public static Date stringToDateFormat(String date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date novaData = new Date();
        try{
            novaData = dateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return novaData;
    }


}
