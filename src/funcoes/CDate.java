/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package funcoes;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author User
 */
public class CDate {

    /**
     * Converte String data formato MYSQL (yyyy-MM-dd) e retorna uma String data formato BR (dd/MM/yyyy)
     * @param data_ yyyy-MM-dd
     * @return dd/MM/yyyy
     */
    public static String DataMySQLtoDataStringPT(String data_) {
        Date data = null;
        try {
            data = new SimpleDateFormat("yyyy-MM-dd").parse(data_);
        } catch (ParseException ex) {
            System.err.println("Erro ao converter data :" + ex);
        }
        return new SimpleDateFormat("dd/MM/yyyy").format(data);
    }

    /**
     *
     * @param data_
     * @return
     */
    public static String DataMySQLtoDataStringPT(Date data_) {
        return new SimpleDateFormat("dd/MM/yyyy").format(data_);
    }

    /**
     *
     * @return
     */
    public static String DataPTBRAtual() {
        return new SimpleDateFormat("dd/MM/yyyy").format(new Date());
    }
    public static String DataPTBRAtualAbreviado() {
        return new SimpleDateFormat("dd/MM/yy").format(new Date());
    }
    /**
     *
     * @param temp
     * @return
     */
    public static String DataPTBRtoDataMySQL(String temp) {
        Date data = new Date();
        try {
            data = new SimpleDateFormat("dd/MM/yyyy").parse(temp);
        } catch (ParseException ex) {
            System.err.println("Erro ao converter data :" + ex);
        }
        return new SimpleDateFormat("yyyy-MM-dd").format(data);
    }

    /**
     *
     * @param dias
     * @param dataInicial_
     * @return
     */
    public static String SomarData(int dias, String dataInicial_) {
        try {
            Date dataInicial =  new SimpleDateFormat("dd/MM/yyyy").parse(dataInicial_);
            Calendar calendarData = Calendar.getInstance();
            calendarData.setTime(dataInicial);
            calendarData.add(Calendar.DATE, dias);
            return new SimpleDateFormat("dd/MM/yyyy").format(calendarData.getTime());
        } catch (ParseException ex) {
            System.err.println("Erro no CDate.SomarData:"+ex.getMessage());
            return dataInicial_;
        }
    }
    
    public static String SomarData_MES(int mes, String dataInicial_) {
        try {
            Date dataInicial =  new SimpleDateFormat("dd/MM/yyyy").parse(dataInicial_);
            Calendar calendarData = Calendar.getInstance();
            calendarData.setTime(dataInicial);
            calendarData.add(Calendar.MONTH, mes);
            return new SimpleDateFormat("dd/MM/yyyy").format(calendarData.getTime());
        } catch (ParseException ex) {
            System.err.println("Erro no CDate.SomarData:"+ex.getMessage());
            return dataInicial_;
        }
    }

    /**
     *
     * @param dataInicio
     * @return
     * @throws Exception
     */
    public static long diasRestantes(String dataInicio) throws Exception {
        int dias = 0;
        Calendar data1 = Calendar.getInstance(), data2 = Calendar.getInstance();
        try {
            data2.setTime(new SimpleDateFormat("dd/MM/yyyy").parse(dataInicio));
        } catch (java.text.ParseException e) {
        }
        if (data1.get(Calendar.YEAR) == data2.get(Calendar.YEAR)){
            dias = data2.get(Calendar.DAY_OF_YEAR)- data1.get(Calendar.DAY_OF_YEAR);
        }
        else if (data1.get(Calendar.YEAR) < data2.get(Calendar.YEAR)){
            int bix=0;
            if (data1.get(Calendar.YEAR)%4==0 && data1.get(Calendar.YEAR)%100!=0 && data1.get(Calendar.YEAR)%400==0)
                bix = 366;
            else
                bix = 365;
            dias = data2.get(Calendar.DAY_OF_YEAR)+bix - data1.get(Calendar.DAY_OF_YEAR);
        }
        return dias;
    }

    /**
     *
     * @param data_
     * @return
     * @throws Exception
     */
    public static Date DataPTBRStringToDate(String data_) throws Exception {
        try {
            return new SimpleDateFormat("dd/MM/yyyy").parse(data_);
        } catch (ParseException ex) {
            throw new Exception("Erro ao converter data. " + ex);
        }
    }

    /**
     *
     * @param data_
     * @return
     * @throws Exception
     */
    public static Date DataMYSQLtoDate(String data_) throws Exception {
        try {
            return new SimpleDateFormat("dd/MM/yyyy").parse(DataMySQLtoDataStringPT(data_));
        } catch (ParseException ex) {
            throw new Exception("Erro ao converter data. " + ex);
        }
    }

    public static String InicialBanco() {
        return "07/10/1997";
    }
    
    public static String getHoraAtualPTBR(){
        Calendar calendario = Calendar.getInstance();
        int hora,minuto,segundo;
        hora = calendario.get(Calendar.HOUR_OF_DAY);
        minuto = calendario.get(Calendar.MINUTE);
        segundo = calendario.get(Calendar.SECOND);
        String h,m,s;
        if (hora<10){
            h = "0"+hora;
        }else{
            h = ""+hora;
        }
        if (minuto<10){
            m = "0"+minuto;
        }else{
            m = ""+minuto;
        }
        if (segundo<10){
            s = "0"+segundo;
        }else{
            s = ""+segundo;
        }
        return (h+":"+m+":"+s);
    }
}
