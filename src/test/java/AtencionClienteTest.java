import com.prueba.enums.TipoCuenta;
import com.prueba.model.Titular;
import com.prueba.services.OperacionesBancarias;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;

public class AtencionClienteTest {

    @Test
    public void nuevoCliente(){

        OperacionesBancarias operacionesBancarias = new OperacionesBancarias();

        Titular nuevoCliente = operacionesBancarias.nuevoTitular("Servando", "Reyes", 95698933,
                "Uruguay 981 6A", new Date("09/03/1988"), "1126579087",
                "andres92898@gmail.com");

        Assert.assertNotNull( nuevoCliente );

    }


    @Test
    public void aperturarCuenta(){

        OperacionesBancarias operacionesBancarias = new OperacionesBancarias();

        Titular nuevoCliente = operacionesBancarias.nuevoTitular("Servando", "Reyes", 95697433,
                "Uruguay 981 6A", new Date("09/03/1988"), "1126579087",
                "andres92898@gmail.com");

        Assert.assertNotNull( nuevoCliente );

        boolean cuentaCreada = operacionesBancarias.crearCuenta(TipoCuenta.CAJA_AHORRO.tipo, TipoCuenta.CAJA_AHORRO.nombre,
                "0102603167091202919218", nuevoCliente, 500000, 0.2);

        Assert.assertTrue( cuentaCreada );

    }

}
