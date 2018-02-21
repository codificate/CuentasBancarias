package com.prueba.enums;

public enum ResourcesPath {

    MOVIMIENTOS("/Users/github/IdeaProjects/CuentasBancarias/src/main/resources/movimientos/"),
    TITULARES("/Users/github/IdeaProjects/CuentasBancarias/src/main/resources/titulares/"),
    CUENTAS("/Users/github/IdeaProjects/CuentasBancarias/src/main/resources/cuentas/");

    public String path;

    ResourcesPath(String s) {
        this.path = s;
    }
}
