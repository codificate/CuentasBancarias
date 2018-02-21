import com.prueba.exceptions.CustomException;
import com.prueba.services.OperacionesBancarias;
import org.junit.Assert;
import org.junit.Test;

public class TaquillaTest {

    @Test
    public void retirarDinero(){

        boolean processIsDone = false;

        OperacionesBancarias operacionesBancarias = new OperacionesBancarias();

        try {
            processIsDone = operacionesBancarias.extraer( "0102603167091202919218", 250 );
        } catch (CustomException e) {
            System.out.println( e.getMessage() );
        }

        Assert.assertTrue( processIsDone );

    }

    @Test
    public void depositarDinero(){

        boolean processIsDone = false;

        OperacionesBancarias operacionesBancarias = new OperacionesBancarias();

        try{
            processIsDone = operacionesBancarias.depositar( "0102603167091202919218", 370 );
        } catch (CustomException e) {
            System.out.println( e.getMessage() );
        }

        Assert.assertTrue( processIsDone );
    }
}
