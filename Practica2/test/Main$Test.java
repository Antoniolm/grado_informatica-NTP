import scala.Char;

import org.scalatest.junit.AssertionsForJUnit
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by LENOVO on 18/04/2017.
 */
public class Main$Test {
    @org.junit.Test
    public void contarCambiosPosibles() throws Exception {

    }

    @org.junit.Test
    public void calcularValorTrianguloPascal() throws Exception {

    }

    @org.junit.Test
    public void chequearBalance() throws Exception {
        //val lista2:List[Char]=List('(','i','f','(','a', '¿','b',')' ,'(','b','/','a',')','e','l','s','e', '(','a','/','(','b'
        //  ,'*','b',')',')',')')
        //val lista2:List[Char]=List('(','c','c','c','(','c','c','c',')','c','c','(','(','c','c','c','(','c',')',')',')',')')

        //val lista2:List[Char]=List('(',')',')','(')
        //val lista2:List[Char]=List('(',')',')','(',')','(',')',')' )
        val lista2:List[Char]=List('(','c','c','c','(','c','c','c','c','c','(','(','c','c','c','(','c',')',')',')',')');
        List<Char> lista3= new List<Char>();
        System.out.println("Resultado="+chequearBalance(lista3));
    }

    @org.junit.Test
    public void busquedaBinaria() throws Exception {

    }

}