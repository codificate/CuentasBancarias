package com.prueba.contracts;

import com.prueba.exceptions.CustomException;

import java.util.Date;

public interface ValidateTitular {

    /**
     *
     * [EN]
     * This function allows to validate every field of Titular class
     * depending of particular conditions
     *
     * [ES]
     * Esta funcion permite validar cada campo de la clase Titular
     * dependiendo de condiciones particulares
     *
     * @param nombre String
     * @param apellido String
     * @param dni int
     * @param fechaNacimiento Date
     * @param numeroTelefono String
     * @param email String
     * @return exception CustomException
     */
    CustomException validateFields(String nombre, String apellido, int dni, Date fechaNacimiento, String numeroTelefono, String email);

}
