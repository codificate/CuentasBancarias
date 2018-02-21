package com.prueba.contracts;

import com.prueba.exceptions.CustomException;
import com.prueba.model.Cuenta;
import com.prueba.model.Titular;

import java.util.ArrayList;

public interface OperacionesCuenta {

    /**
     *
     * @param tipo int
     * @param tipoNombre int
     * @param nCuenta String
     * @param titular Titular
     * @param saldoInicial double
     * @param desAgregado double
     * @return boolean creada
     */
    boolean crearCuenta(int tipo, String tipoNombre, String nCuenta, Titular titular, double saldoInicial, double desAgregado);

    /**
     *
     * @param nCuenta String
     * @return Cuenta cuenta
     */
    Cuenta consultarCuenta(String nCuenta);

    /**
     *
     * @param dni int
     * @return Cuenta cuenta
     */
    ArrayList<Cuenta> consultarCuentasByTitular(int dni);

    /**
     *
     * @param nCuenta String
     * @param monto double
     * @return boolean deposito
     * @throws CustomException exception
     */
    boolean depositar(String nCuenta, double monto) throws CustomException;

    /**
     *
     * @param nCuenta String
     * @param monto double
     * @return boolean retiro
     * @throws CustomException exception
     */
    boolean extraer(String nCuenta, double monto) throws CustomException;

    /**
     *
     * @param tipo String
     * @param nCuenta String
     * @param monto double
     */
    void actualizarMovimientosCuenta(String tipo, String nCuenta, double monto, double saldo);


}
