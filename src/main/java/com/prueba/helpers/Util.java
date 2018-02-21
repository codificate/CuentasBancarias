package com.prueba.helpers;

import com.prueba.enums.TipoCuenta;
import com.prueba.enums.TipoOperacion;
import com.prueba.model.Tipo;
import com.sun.deploy.util.StringUtils;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Util {

    private static Pattern pattern;
    private static Matcher matcher;

    private static final String EMAIL_PATTERN =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    /**
     *
     * [EN]
     * This function allows to validate if an email has a correct format
     *
     * [ES]
     * Esta funcion permite validar si un correo electronico tiene un formato correcto
     *
     * @param email String
     * @return boolean
     */
    public static boolean validateEmail( String email ){

        pattern = Pattern.compile(EMAIL_PATTERN);

        matcher = pattern.matcher(email);
        return matcher.matches();
    }


    /**
     *
     * [EN]
     * This function allows to validate if a String is null or empty
     *
     * [ES]
     * Esta funcion permite validar si un texto es nulo o vacio
     *
     * @param value String
     * @return boolean
     */
    public static boolean validateStringNullOrEmpty( String value ){

        boolean isValid = true;

        if ( value == null ){
            isValid = false;
        } else if ( value.trim().isEmpty() ){
            isValid = false;
        }

        return isValid;

    }

    /**
     *
     * [EN]
     * This function allows to validate if a number is a DNI number accepted
     *
     * [ES]
     * Esta funcion permite validar si un numero es un numero de DNI aceptado
     *
     * @param dni int
     * @return boolean
     */
    public static boolean validateDni( int dni ){

        boolean isValid = true;

        if ( String.valueOf( dni ).length() < 6 || String.valueOf( dni ).length() > 9){
            isValid = false;
        }

        return isValid;

    }

    /**
     *
     * [EN]
     * This function allows to validate if an object is not null
     *
     * [ES]
     * Esta funcion permite validar si un objeto no es nulo
     *
     * @param value Object
     * @return boolean
     */
    public static boolean validateNotNull( Object value ){

        boolean isValid = true;

        if ( value == null ){
            isValid = false;
        }

        return isValid;

    }

    /**
     *
     * [EN]
     * This function allows to validate if an object is not null
     *
     * [ES]
     * Esta funcion permite validar si un objeto no es nulo
     *
     * @param numberAccount long
     * @return boolean
     */
    public static boolean validateNumberAccount( String numberAccount ){

        boolean isValid = true;

        String regex = "\\d+";
        Pattern pattern = Pattern.compile(regex);

        if ( pattern.matcher( numberAccount ).matches() && numberAccount.length() < 22){
            isValid = false;
        }

        return isValid;
    }


    /**
     *
     * [EN]
     * This function allows to validate if TipoCuenta exists in list
     *
     * [ES]
     * Esta funcion permite validar si el TipoCuenta existe en la lista
     *
     * @param tipo int
     * @return boolean
     */
    public static boolean validateTipoCuenta( int tipo ){

        boolean isValid = false;

        List<TipoCuenta> tipoCuentasList = new ArrayList<TipoCuenta>(EnumSet.allOf(TipoCuenta.class));

        for (TipoCuenta t : tipoCuentasList){
            if ( t.tipo == tipo )
                isValid = true;
        }

        return isValid;

    }

    /**
     *
     * [EN]
     * This function allows to validate if TipoOperacion exists in list
     *
     * [ES]
     * Esta funcion permite validar si el TipoOperacion existe en la lista
     *
     * @param tipo
     * @return
     */
    public static boolean validateTipoOperacion( int tipo ){

        boolean isValid = true;

        List<TipoOperacion> tipoOperacionList = new ArrayList<TipoOperacion>(EnumSet.allOf(TipoOperacion.class));

        if ( !tipoOperacionList.contains( tipo ) ){
            isValid = false;
        }

        return isValid;
    }

    public static void showInConsole( boolean isDone, String operacion, String mensaje){

        if (isDone){
            System.out.println("-------------------------------------------------------");
            System.out.println("\033[32".concat( operacion ).concat("\033[0m"));
            System.out.println("\033[32".concat( mensaje ).concat("\033[0m"));
            System.out.println("-------------------------------------------------------");
        }
        else {
            System.out.println("-------------------------------------------------------");
            System.out.println("\033[33m".concat( operacion ).concat("\033[0m"));
            System.out.println("\033[33m".concat( mensaje ).concat("\033[0m"));
            System.out.println("-------------------------------------------------------");

        }
    }

}
