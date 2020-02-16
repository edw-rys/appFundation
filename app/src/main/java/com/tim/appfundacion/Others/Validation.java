package com.tim.appfundacion.Others;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation {
    public static boolean isNumber(String value) {
        boolean status=false;
        int number=-1;
        try {
            number=Integer.parseInt(value);
            status=true;
        } catch(NumberFormatException ex)
        {
            //System.out.println(ex.toString());
            status=false;
        }
        return status;
    }

    public static boolean isPhoneNumber(String value){
        if (isNumber(value)){
            if (value.length()==10)
                return true;
        }
        return false;
    }
    public static boolean isDecimal(String value) {
        boolean status=false;
        float number=-1;
        try {
            number=Float.parseFloat(value);
            status=true;
        } catch(NumberFormatException ex)
        {
            //System.out.println(ex.toString());
            status=false;
        }
        return status;
    }
    public static boolean isMail(String value){
        Pattern patron = Pattern.compile("^\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*(\\.\\w{2,3})+$");
        // creamos el Matcher a partir del patron, la cadena como parametro
        boolean vData=false;
        Matcher esEmail = patron.matcher("");
        esEmail = patron.matcher(value);
        return esEmail.find();

    }
    public static boolean isDNIEC(String value) {
        //Declaración de variables a usar
        String valida="oooooooo";
        byte primeros2, tercerD, Dverificador, multiplicar, suma=0, aux;
        byte []digitos=new byte[9];
        boolean status=false;
        //Primer try comprueba la longitud de cadena que no sea diferente de 10
        try {
            valida=value;
            if(valida.length()!=10)
                return false;
            //Segundo try comprueba que todos los dígitos sean numéricos
            try {

                //Transformación de cada carácter a un byte
                Dverificador=Byte.parseByte(""+valida.charAt(9));
                status=true;
            }
            catch(NumberFormatException e) {
                return false;
            }

        }
        catch(Exception e) {
            status =false;
            //System.out.println(e.getMessage());
        }
        return status;
    }
    public static boolean validateNames(String values){
        Pattern patron = Pattern.compile("^(?=.{4,20}$)[A-ZÁÉÍÓÚ][a-zñáéíóú]+(?: [A-ZÁÉÍÓÚ][a-zñáéíóú]+)?$");
        Matcher status = patron.matcher(values);
        return status.find();
    }
    //metodo para validar si la fecha es correcta
    public static boolean isDate(String fechax) {
        try {
            SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
            Date fecha = formatoFecha.parse(fechax);


        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public static boolean isText(String value){
        String REGEX_LETRAS = "^[a-zA-ZáÁéÉíÍóÓúÚñÑüÜ\\s]+$";
        Pattern patron = Pattern.compile(REGEX_LETRAS);
        return patron.matcher(value).matches();
    }
}
