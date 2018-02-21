import com.prueba.callables.Depositar;
import com.prueba.callables.Retirar;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

public class CajeroAutomaticoTest {

    @Test
    public void manipularCuenta(){

        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(3);
        List<Future<Boolean>> resultList = new ArrayList<>();

        Random random = new Random();

        for ( int i=0; i<10; i++ ){

            int operacion = random.nextInt(10);

            if ( operacion % 2 == 0 ){

                Depositar depositar = new Depositar( "0102603167091202919218", amount() );
                Future<Boolean> result = executor.submit(depositar);
                resultList.add(result);

            } else {

                Retirar retirar = new Retirar("0102603167091202919218", amount());
                Future<Boolean> result = executor.submit(retirar);
                resultList.add(result);
            }

        }

        Assert.assertNotNull( resultList );

        for(Future<Boolean> future : resultList)
        {
            try
            {
                System.out.println("Operacion en proceso: " + future.get());
            }
            catch (InterruptedException | ExecutionException e)
            {
                e.printStackTrace();
            }
        }

        executor.shutdown();

    }

    private double amount(){
        Random r = new Random();
        int Low = 100;
        int High = 3000;
        return r.nextInt(High-Low) + Low;
    }

}
