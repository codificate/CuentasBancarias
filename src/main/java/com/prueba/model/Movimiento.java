package com.prueba.model;

import com.prueba.contracts.ValidateMovimiento;
import com.prueba.enums.CommonErrors;
import com.prueba.exceptions.CustomException;
import com.prueba.helpers.Util;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Movimiento implements ValidateMovimiento, Serializable {

    private String numeroCuenta;
    private String fechaTransaccion;
    private String tipoOperacion;
    private double monto;
    private double saldo;

    /**
     *
     * @param numeroCuenta String
     * @param tipoOperacion int
     * @param monto double
     * @param saldo double
     */
    public Movimiento(String tipoOperacion, String numeroCuenta, double monto, double saldo) throws CustomException {

        CustomException exception = validateFields(numeroCuenta);

        if (null != exception ){
            throw exception;
        }

        this.numeroCuenta = numeroCuenta;
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        this.fechaTransaccion = dateFormat.format(date);
        this.tipoOperacion = tipoOperacion;
        this.monto = monto;
        this.saldo = saldo;
    }

    @Override
    public CustomException validateFields(String numeroCuenta){

        CustomException exception = null;

        if (!Util.validateNumberAccount(numeroCuenta)){
            exception = new CustomException(CommonErrors.INVALIDACCOUNT.msg);
        }

        return exception;
    }

    /**
     *
     * @return long numeroCuenta
     */
    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    /**
     *
     * @return String fechaTransaccion
     */
    public String getFechaTransaccion() {
        return fechaTransaccion;
    }

    /**
     *
     * @return String tipoOperacion
     */
    public String getTipoOperacion() {
        return tipoOperacion;
    }

    /**
     *
     * @return double monto
     */
    public double getMonto() {
        return monto;
    }


    /**
     *
     * @return double saldo
     */
    public double getSaldo() {
        return saldo;
    }
}
