package com.prueba.enums;

public enum TipoCuenta {

    CAJA_AHORRO( 1, "CAJA_AHORRO"),
    CTA_CORRIENTE( 2, "CTA_CORRIENTE");

    public int tipo;
    public String nombre;

    TipoCuenta(int tipo, String nombre) {
        this.tipo = tipo;
        this.nombre = nombre;
    }
}
