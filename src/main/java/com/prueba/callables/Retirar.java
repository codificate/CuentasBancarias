package com.prueba.callables;

import com.prueba.enums.TipoOperacion;
import com.prueba.exceptions.CustomException;
import com.prueba.helpers.Util;
import com.prueba.services.OperacionesBancarias;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public class Retirar implements Callable<Boolean> {

    String numeroCuenta;
    double monto;

    public Retirar(String numeroCuenta, double monto) {
        this.numeroCuenta = numeroCuenta;
        this.monto = monto;
    }

    @Override
    public Boolean call() throws Exception {


        boolean processIsDone = false;

        OperacionesBancarias operacionesBancarias = new OperacionesBancarias();

        try {

            processIsDone = operacionesBancarias.extraer( this.numeroCuenta, this.monto );
            TimeUnit.MILLISECONDS.sleep(3000);

        } catch (CustomException e) {

            Util.showInConsole( false, TipoOperacion.EXTRACCION.nombre, e.getMessage() );
            return false;

        }

        Util.showInConsole( processIsDone, TipoOperacion.EXTRACCION.nombre, "Transaccion satisfactoria");

        return processIsDone;
    }
}
