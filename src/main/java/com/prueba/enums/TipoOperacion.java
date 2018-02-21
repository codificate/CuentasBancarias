package com.prueba.enums;

public enum TipoOperacion {

    DEPOSITO(1, "Depositar"),
    EXTRACCION(2, "Extraccion");

    public int tipo;
    public String nombre;


    TipoOperacion(int tipo, String nombre) {
        this.tipo = tipo;
        this.nombre = nombre;
    }
}
