package com.prueba.contracts;

import com.prueba.exceptions.CustomException;
import com.prueba.model.Titular;

public interface ValidateCuenta {

    /**
     *
     * [EN]
     * This function allows to validate every field of Cuenta class
     * depending of particular conditions
     *
     * [ES]
     * Esta funcion permite validar cada campo de la clase Cuenta
     * dependiendo de condiciones particulares
     *
     * @param tipo int
     * @param numeroCuenta long
     * @param titular Titular
     * @param saldo double
     * @return exception CustomException
     */
    CustomException validateFields(int tipo, String numeroCuenta, Titular titular, double saldo);

}
