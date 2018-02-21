package com.prueba.controllers;

import com.prueba.model.CuentasTitular;
import com.prueba.model.Titular;
import com.prueba.services.OperacionesBancarias;

import java.util.Date;

public class AtencionCliente {

    OperacionesBancarias operacionesBancarias;

    public AtencionCliente() {

        operacionesBancarias = new OperacionesBancarias();

    }

    public Titular nuevoCliente( String nombre, String apellido, int dni, String direccion, String fecha, String telefono, String email ){

        return operacionesBancarias.nuevoTitular( nombre, apellido, dni, direccion, new Date(fecha), telefono, email);

    }

    public boolean aperturarCuenta( int tipo, String tipoNombre, String nCuenta, Titular titular, double saldoInicial, double descAcordado ){

        return operacionesBancarias.crearCuenta( tipo, tipoNombre, nCuenta, titular, saldoInicial, descAcordado);

    }

    public CuentasTitular consultarDatosCliente( int dni ){

        return operacionesBancarias.consultarTitular( dni );

    }
}
