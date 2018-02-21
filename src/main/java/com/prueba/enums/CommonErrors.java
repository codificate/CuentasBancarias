package com.prueba.enums;

public enum CommonErrors {

    INVALIDACCOUNT("Numero de cuenta invalido"),
    INVALIDACCOUNTTYPE("Tipo de cuenta invalido"),
    INVALIDOWNER("El titular de la cuenta es invalido"),
    EMPTYFIELD("El campo [%s] no puede estar vacio"),
    EMAILINCORRECT("El email es incorrecto"),
    INSUFICIENTBALANCE("El saldo insifuciente"),
    NEGATIVECHARGEAMOUNT("El monto a depositar en la cuenta no puede ser negativo"),
    NEGATIVESUBSTRACTAMOUNT("El monto a retirar en la cuenta no puede ser negativo"),
    NEGATIVEBALANCE("El saldo de la cuenta no puede ser negativo");

    public String msg;

    CommonErrors(String msg) {
        this.msg = msg;
    }

}
