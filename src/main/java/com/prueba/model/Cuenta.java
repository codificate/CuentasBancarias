package com.prueba.model;

import com.prueba.contracts.ValidateCuenta;
import com.prueba.enums.CommonErrors;
import com.prueba.enums.TipoCuenta;
import com.prueba.exceptions.CustomException;
import com.prueba.helpers.Util;

import java.io.Serializable;

public class Cuenta implements ValidateCuenta, Serializable {

    protected int tipo;
    protected String numeroCuenta;
    protected Titular titular;
    protected double saldo;
    protected double descubiertoAgregado;

    /**
     *
     * @param tipo int
     * @param numeroCuenta String
     * @param titular Titular
     * @param saldo double
     * @param descubiertoAgregado double
     */
    public Cuenta(int tipo, String numeroCuenta, Titular titular, double saldo, double descubiertoAgregado) throws CustomException {

        CustomException exception = validateFields(tipo, numeroCuenta, titular, saldo);

        if (null != exception ){
            throw exception;
        }

        this.tipo = tipo;
        this.numeroCuenta = numeroCuenta;
        this.titular = titular;
        this.saldo = saldo;

        if ( tipo == TipoCuenta.CTA_CORRIENTE.tipo ) {
            this.descubiertoAgregado = descubiertoAgregado;
        } else {
            this.descubiertoAgregado = 0;
        }

    }

    @Override
    public CustomException validateFields(int tipo, String numeroCuenta, Titular titular, double saldo) {

        CustomException exception = null;

        if ( !Util.validateTipoCuenta( tipo ) ){
            exception = new CustomException(CommonErrors.INVALIDACCOUNTTYPE.msg);
        }

        if ( !Util.validateNumberAccount(numeroCuenta) ){
            exception = new CustomException(CommonErrors.INVALIDACCOUNT.msg);
        }

        if ( !Util.validateNotNull(titular) ){
            exception = new CustomException(CommonErrors.INVALIDOWNER.msg);
        }

        if ( saldo < 0 ){
            exception = new CustomException(CommonErrors.NEGATIVEBALANCE.msg);
        }

        return exception;
    }


    /**
     *
     * @return tipo int
     */
    public int getTipo() {
        return tipo;
    }

    /**
     *
     * @return numeroCuenta long
     */
    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    /**
     *
     * @return titular Titular
     */
    public Titular getTitular() {
        return titular;
    }

    /**
     *
     * @return saldo double
     */
    public double getSaldo() {
        return saldo;
    }

    /**
     *
     * @return descubiertoAgregado double
     */
    public double getDescubiertoAgregado() {
        return descubiertoAgregado;
    }

    /**
     *
     * @param cuentaConsultada Cuenta
     * @param monto double
     * @return Cuenta cuentaConsultada
     */
    public Cuenta actualizarSaldoPositivo( Cuenta cuentaConsultada, double monto ){

        cuentaConsultada.saldo += monto;

        return cuentaConsultada;
    }

    /**
     *
     * @param cuentaConsultada Cuenta
     * @param monto double
     * @return Cuenta cuentaConsultada
     */
    public Cuenta actualizarSaldoNegativo( Cuenta cuentaConsultada, double monto ){

        cuentaConsultada.saldo -= monto;

        return cuentaConsultada;
    }

}
