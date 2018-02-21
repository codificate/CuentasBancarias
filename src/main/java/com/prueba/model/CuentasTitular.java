package com.prueba.model;

import java.util.ArrayList;

public class CuentasTitular {

    Titular titular;
    ArrayList<Cuenta> cuentas;

    public CuentasTitular(Titular titular, ArrayList<Cuenta> cuentas) {
        this.titular = titular;
        this.cuentas = cuentas;
    }

    public Titular getTitular() {
        return titular;
    }

    public ArrayList<Cuenta> getCuentas() {
        return cuentas;
    }
}
