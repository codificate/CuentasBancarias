package com.prueba.model;

import com.prueba.contracts.ValidateTitular;
import com.prueba.enums.CommonErrors;
import com.prueba.exceptions.CustomException;
import com.prueba.helpers.Util;

import java.io.Serializable;
import java.util.Date;

public class Titular implements ValidateTitular, Serializable {

    private String nombre;
    private String apellido;
    private int dni;
    private String direccion;
    private Date fechaNacimiento;
    private String numeroTelefono;
    private String email;

    /**
     *
     * @param nombre String
     * @param apellido String
     * @param dni int
     * @param fechaNacimiento Date
     * @param numeroTelefono String
     * @param email String
     */
    public Titular(String nombre, String apellido, int dni, String direccion, Date fechaNacimiento, String numeroTelefono, String email)  throws CustomException {

        CustomException exception = validateFields(nombre, apellido, dni, fechaNacimiento, numeroTelefono, email);

        if (null != exception ){
            throw exception;
        }

        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.direccion = direccion;
        this.fechaNacimiento = fechaNacimiento;
        this.numeroTelefono = numeroTelefono;
        this.email = email;
    }

    @Override
    public CustomException validateFields(String nombre, String apellido, int dni, Date fechaNacimiento, String numeroTelefono, String email) {

        CustomException exception = null;

        if (!Util.validateStringNullOrEmpty(nombre)){
            exception = new CustomException( String.format( CommonErrors.EMPTYFIELD.msg, "Nombre" ) );
        }

        if (!Util.validateStringNullOrEmpty(apellido)){
            exception = new CustomException( String.format( CommonErrors.EMPTYFIELD.msg, "Apellido" ) );
        }

        if (!Util.validateDni(dni)){
            exception = new CustomException( String.format( CommonErrors.EMPTYFIELD.msg, "DNI" ) );
        }

        if (!Util.validateNotNull(fechaNacimiento)){
            exception = new CustomException( String.format( CommonErrors.EMPTYFIELD.msg, "Fecha de nacimiento" ) );
        }

        if (!Util.validateStringNullOrEmpty(numeroTelefono)){
            exception = new CustomException( String.format( CommonErrors.EMPTYFIELD.msg, "Numero de telefono" ) );
        }

        if (!Util.validateEmail(email)){
            exception = new CustomException( CommonErrors.EMAILINCORRECT.msg );
        }

        return exception;
    }


    /**
     *
     * @return String nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     *
     * @return String apellido
     */
    public String getApellido() {
        return apellido;
    }

    /**
     *
     * @return int dni
     */
    public int getDni() {
        return dni;
    }

    /**
     *
     * @return Date fechaNacimiento
     */
    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    /**
     *
     * @return numeroTelefono String
     */
    public String getNumeroTelefono() {
        return numeroTelefono;
    }

    /**
     *
     * @return String email
     */
    public String getEmail() {
        return email;
    }
}
