package com.prueba.contracts;

import com.prueba.exceptions.CustomException;

import java.util.Date;

public interface ValidateMovimiento {

    /**
     *
     * [EN]
     * This function allows to validate every field of Movimiento class
     * depending of particular conditions
     *
     * [ES]
     * Esta funcion permite validar cada campo de la clase Movimiento
     * dependiendo de condiciones particulares
     *
     * @param numeroCuenta String
     * @return exception CustomException
     */
    CustomException validateFields(String numeroCuenta);

}
