package com.prueba.contracts;

import com.prueba.model.Cuenta;
import com.prueba.model.CuentasTitular;
import com.prueba.model.Titular;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

public interface OperacionesTitular {

    /**
     *
     * @param nombre String
     * @param apellido String
     * @param dni String
     * @param direccion String
     * @param fechaNacimiento Date
     * @param telefono String
     * @param email String
     * @return Titular titular
     */
    Titular nuevoTitular(String nombre, String apellido, int dni, String direccion,
                         Date fechaNacimiento, String telefono, String email);

    /**
     *
     * @param dni String
     * @return CuentasTitular informacionTitular
     */
    CuentasTitular consultarTitular(int dni);

}
