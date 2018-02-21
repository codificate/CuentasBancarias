package com.prueba.services;

import com.google.gson.reflect.TypeToken;
import com.prueba.contracts.OperacionesCuenta;
import com.prueba.contracts.OperacionesTitular;
import com.prueba.enums.CommonErrors;
import com.prueba.enums.ResourcesPath;
import com.prueba.enums.TipoOperacion;
import com.prueba.exceptions.CustomException;
import com.prueba.helpers.FileContext;
import com.prueba.helpers.GsonSingleton;
import com.prueba.model.Cuenta;
import com.prueba.model.CuentasTitular;
import com.prueba.model.Movimiento;
import com.prueba.model.Titular;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.*;

public class OperacionesBancarias implements OperacionesCuenta, OperacionesTitular {

    @Override
    public boolean crearCuenta(int tipo, String tipoNombre, String nCuenta, Titular titular, double saldoInicial, double desAgregado) {

        boolean creada = false;

        Cuenta nuevaCuenta;

        try {

            if ( null == consultarCuenta( nCuenta ) ){

                nuevaCuenta = new Cuenta(tipo,nCuenta, titular, saldoInicial, desAgregado);

                String cuentaBody = GsonSingleton.getInstance().getGson().toJson(nuevaCuenta);

                FileContext.writeFile(ResourcesPath.CUENTAS.path.concat( String.valueOf(nCuenta) ), cuentaBody);

                creada = !creada;

                actualizarMovimientosCuenta( tipoNombre, nCuenta, saldoInicial, saldoInicial);

                ArrayList<Cuenta> cuentas = consultarCuentasByTitular(titular.getDni());
                List<String> numeroCuentasPorCliente = new ArrayList<String>();

                if ( null != cuentas ){

                    for ( Cuenta cuenta : cuentas ){

                        numeroCuentasPorCliente.add( cuenta.getNumeroCuenta() );

                    }

                }

                numeroCuentasPorCliente.add( nuevaCuenta.getNumeroCuenta() );

                String cuentasTitularBody = GsonSingleton.getInstance().getGson().toJson(numeroCuentasPorCliente);

                FileContext.writeFile(ResourcesPath.TITULARES.path.concat( String.valueOf( titular.getDni() ) ).concat("-cuentas"), cuentasTitularBody);

            }


        } catch ( CustomException ife ){

            System.out.println( ife.getMessage() );

        } catch (IOException e) {

            e.getMessage();

        }

        return creada;
    }

    @Override
    public Cuenta consultarCuenta(String nCuenta) {

        Cuenta cuenta = null;

        String Path = ResourcesPath.CUENTAS.path.concat( String.valueOf(nCuenta) );

        String cuentaEncontrada = FileContext.readFile( Path);

        if ( null != cuentaEncontrada ){

            Type collectionType = new TypeToken<Cuenta>() {}.getType();
            cuenta = GsonSingleton.getInstance().getGson().fromJson( cuentaEncontrada, collectionType);

            try {
                if ( null == cuenta.getNumeroCuenta() || cuenta.getNumeroCuenta().trim().isEmpty() )
                    cuenta = null;
            } catch (NullPointerException e) {
                cuenta = null;
            }

        }

        return cuenta;
    }

    @Override
    public ArrayList<Cuenta> consultarCuentasByTitular(int dni) {

        ArrayList<Cuenta> cuentas = null;

        CuentasTitular cuentasDelTitular = consultarTitular( dni );

        if (null != cuentasDelTitular){

            cuentas = cuentasDelTitular.getCuentas();
        }

        return cuentas;
    }

    @Override
    public boolean depositar(String nCuenta, double monto) throws CustomException {

        Cuenta cuenta = consultarCuenta( nCuenta );

        if ( monto < 0 ){
            throw new CustomException(CommonErrors.NEGATIVECHARGEAMOUNT.msg);
        }

        if ( null == cuenta ){
            throw new CustomException(CommonErrors.INVALIDACCOUNT.msg);
        } else {
            cuenta.actualizarSaldoPositivo( cuenta, monto );
            actualizarMovimientosCuenta(TipoOperacion.DEPOSITO.nombre, cuenta.getNumeroCuenta(), monto, cuenta.getSaldo());


            String cuentaBody = GsonSingleton.getInstance().getGson().toJson(cuenta);

            try {
                FileContext.writeFile(ResourcesPath.CUENTAS.path.concat( String.valueOf(nCuenta) ), cuentaBody);
            } catch (IOException e) {
                System.out.println( e.getMessage() );
            }
        }

        return true;
    }

    @Override
    public boolean extraer(String nCuenta, double monto) throws CustomException {

        Cuenta cuenta = consultarCuenta( nCuenta );

        if ( monto < 0 ){
            throw new CustomException(CommonErrors.NEGATIVESUBSTRACTAMOUNT.msg);
        }

        if ( null == cuenta ){

            throw new CustomException(CommonErrors.INVALIDACCOUNT.msg);

        } else {

            if ( cuenta.getSaldo() + ( monto * -1 ) < 0){

                throw new CustomException(CommonErrors.INSUFICIENTBALANCE.msg);

            } else {

                cuenta.actualizarSaldoNegativo( cuenta, monto );
                actualizarMovimientosCuenta(TipoOperacion.EXTRACCION.nombre, cuenta.getNumeroCuenta(), ( monto * -1 ), cuenta.getSaldo());

                String cuentaBody = GsonSingleton.getInstance().getGson().toJson(cuenta);

                try {
                    FileContext.writeFile(ResourcesPath.CUENTAS.path.concat( String.valueOf(nCuenta) ), cuentaBody);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }

        return true;
    }

    @Override
    public void actualizarMovimientosCuenta(String tipo, String nCuenta, double monto, double saldo) {

        ArrayList<Movimiento> movimientosCuenta = new ArrayList<Movimiento>();

        try {

            String movimientosPath = ResourcesPath.MOVIMIENTOS.path.concat(nCuenta);
            String movimientosEncontrado = FileContext.readFile( movimientosPath );

            if ( null != movimientosEncontrado ){

                if ( !movimientosEncontrado.isEmpty() ){

                    Type collectionType = new TypeToken< ArrayList<Movimiento> >() {}.getType();
                    movimientosCuenta = GsonSingleton.getInstance().getGson().fromJson( movimientosEncontrado, collectionType);

                }

            }

            Movimiento nuevoMovimiento = new Movimiento( tipo, nCuenta, monto, saldo );

            movimientosCuenta.add( nuevoMovimiento );

            String nuevoMovimientoBody = GsonSingleton.getInstance().getGson().toJson( movimientosCuenta );

            FileContext.writeFile(movimientosPath, nuevoMovimientoBody);


        } catch (CustomException ife) {

            System.out.println( ife.getMessage() );

        } catch (IOException e) {

            System.out.println( e.getMessage() );

        }

    }

    @Override
    public Titular nuevoTitular(String nombre, String apellido, int dni, String direccion, Date fechaNacimiento, String telefono, String email) {

        Titular nuevoTitular = null;

        try {

            nuevoTitular = new Titular(nombre, apellido, dni, direccion, fechaNacimiento, telefono, email);

            String titularBody = GsonSingleton.getInstance().getGson().toJson(nuevoTitular);

            FileContext.writeFile(ResourcesPath.TITULARES.path.concat( String.valueOf(dni) ), titularBody);

        } catch ( CustomException ife ){

            System.out.println( ife.getMessage() );

        } catch (IOException e) {

            e.getMessage();

        }

        return nuevoTitular;
    }

    @Override
    public CuentasTitular consultarTitular(int dni) {

        Titular titular = null;
        ArrayList<Cuenta> cuentasTitular = null;
        CuentasTitular informacionTitular = null;

        String titularPath = ResourcesPath.TITULARES.path.concat( String.valueOf(dni) );

        String titularEncontrado = FileContext.readFile( titularPath );

        Type collectionType = new TypeToken<Titular>() {}.getType();
        titular = GsonSingleton.getInstance().getGson().fromJson( titularEncontrado, collectionType);

        try {
            if ( titular.getDni() == 0 ) {
                titular = null;
            }
        } catch (NullPointerException e) {
            titular = null;
        }

        if ( null != titular ){

            String cuentasPath = ResourcesPath.TITULARES.path.concat( String.valueOf(dni) ).concat("-cuentas");

            String cuentasEncontradas = FileContext.readFile( cuentasPath );

            if ( null != cuentasEncontradas ){

                cuentasTitular = new ArrayList<Cuenta>();

                Type collectionTypeCuentas = new TypeToken< String[] >() {}.getType();
                String[] cuentas = GsonSingleton.getInstance().getGson().fromJson( cuentasEncontradas, collectionTypeCuentas);

                for ( String numeroCuenta : cuentas ){

                    Cuenta cuenta = consultarCuenta( numeroCuenta );
                    if ( null != cuenta ){
                        cuentasTitular.add( cuenta );
                    }
                }

                informacionTitular = new CuentasTitular(titular, cuentasTitular);

            } else {
                informacionTitular = new CuentasTitular(titular, null);
            }

        }

        return informacionTitular;
    }
}
