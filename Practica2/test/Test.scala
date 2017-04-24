/**
  * Created by Antonio David López Machado on 18/04/2017.
  */

import junit.framework.TestCase
import org.junit.Assert._

class Test extends TestCase{

  /**
    * Test realizado sobre el metodo -> CalcularValorTrianguloPascal
    */
  def testCalcularValorTrianguloPascal {

    // Se muestran 10 filas del trinagulo de Pascal
    for (row <- 0 to 10) {
      // Se muestran 10 10 columnas
      for (col <- 0 to row)
        print(Main.calcularValorTrianguloPascal(col, row) + " ")

      // Salto de linea final para mejorar la presentacion
      println()
    }

    //Comprobamos algunos elementos aleatorios de el triangulo de pascal
    assertEquals(Main.calcularValorTrianguloPascal(10, 15), 3003)
    assertEquals(Main.calcularValorTrianguloPascal(1, 2), 2)

    //Chequeamos todos los elemento de la fila 10 para comprobar su correcto calculo
    assertEquals(Main.calcularValorTrianguloPascal(0, 10), 1)
    assertEquals(Main.calcularValorTrianguloPascal(1, 10), 10)
    assertEquals(Main.calcularValorTrianguloPascal(2, 10), 45)
    assertEquals(Main.calcularValorTrianguloPascal(3, 10), 120)
    assertEquals(Main.calcularValorTrianguloPascal(4, 10), 210)
    assertEquals(Main.calcularValorTrianguloPascal(5, 10), 252)
    assertEquals(Main.calcularValorTrianguloPascal(6, 10), 210)
    assertEquals(Main.calcularValorTrianguloPascal(7, 10), 120)
    assertEquals(Main.calcularValorTrianguloPascal(8, 10), 45)
    assertEquals(Main.calcularValorTrianguloPascal(9, 10), 10)
    assertEquals(Main.calcularValorTrianguloPascal(10, 10), 1)

    //Comprobamos los limites izquierda y derecha del triangulo e pascal
    //ambos limites deben tener un valor de 1 en cada elemento.
    for (i <- 0 until 10) {
      assertEquals(Main.calcularValorTrianguloPascal(0, i), 1)
      assertEquals(Main.calcularValorTrianguloPascal(i, i), 1)
    }

  }

  /**
    * Test realizado sobre el metodo -> ChequearBalance
    */
  def testChequearBalance{

    //Cadenas validas
    var lista2:List[Char]=List('(','i','f','(','a', '¿','b',')' ,'(','b','/','a',')','e','l','s','e', '(','a','/','(','b'
      ,'*','b',')',')',')')
    assertTrue(Main.chequearBalance(lista2));

    lista2=List('(','c','c','c','(','c','c','c',')','c','c','(','(','c','c','c','(','c',')',')',')',')')
    assertTrue(Main.chequearBalance(lista2));

    //Cadenas invalidas
    lista2=List('(',')',')','(')
    assertFalse(Main.chequearBalance(lista2));

    lista2=List('(',')',')','(',')','(',')',')' )
    assertFalse(Main.chequearBalance(lista2));

    lista2=List('(','c','c','c','(','c','c','c','c','c','(','(','c','c','c','(','c',')',')',')',')')
    assertFalse(Main.chequearBalance(lista2));
  }

  /**
    * Test realizado sobre el metodo -> ContarCambiosPosibles
    * */
  def testContarCambiosPosibles{
    var lista:List[Int]=List(3,2,1)
    assertEquals(Main.contarCambiosPosibles(4,lista),4)

    //Si el valor es negativo
    assertEquals(Main.contarCambiosPosibles(-1,lista),0)

    //Con un conjunto de monedas reducido
    lista=List(1)
    assertEquals(Main.contarCambiosPosibles(4,lista),1)

    //Si se pide cambio de 0
    assertEquals(Main.contarCambiosPosibles(0,lista),1)

    //Si no hay conjunto de monedas
    lista=List()
    assertEquals(Main.contarCambiosPosibles(4,lista),0)
  }

  /**
    * Test realizado sobre el metodo -> busqueda binaria
    */
  def testBusquedaBinaria{
    //Busquedas sobre un conjunto
    val lista:List[Int]=List(1,2,4,5,7,45,67,98,123)
    assertEquals(Main.busquedaBinaria(5,lista,(x:Int,y:Int)=>x<y),3)  //Elemento aleatorio
    assertEquals(Main.busquedaBinaria(7,lista,(x:Int,y:Int)=>x<y),4)  //elemento intermemdio
    assertEquals(Main.busquedaBinaria(1,lista,(x:Int,y:Int)=>x<y),0)  //primer elemento
    assertEquals(Main.busquedaBinaria(123,lista,(x:Int,y:Int)=>x<y),8)//ultimo elemento
    assertEquals(Main.busquedaBinaria(46,lista,(x:Int,y:Int)=>x<y),-1)//elemento no disponible


    //Busquedas sobre un conjunto vacio
    val listaEmpty:List[Int]=List()
    assertEquals(Main.busquedaBinaria(2,listaEmpty,(x:Int,y:Int)=>x<y),-1)
  }
}
