package com.prueba.enums;

public enum ResourcesPath {

    MOVIMIENTOS("/Users/andres/IdeaProjects/CuentasBancarias/src/main/resources/movimientos/"),
    TITULARES("/Users/andres/IdeaProjects/CuentasBancarias/src/main/resources/titulares/"),
    CUENTAS("/Users/andres/IdeaProjects/CuentasBancarias/src/main/resources/cuentas/");

    public String path;

    ResourcesPath(String s) {
        this.path = s;
    }
}
