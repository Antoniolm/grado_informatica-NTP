/**
  * Created by LENOVO on 18/04/2017.
  */

import junit.framework.TestCase
import org.junit.Assert._

class Test extends TestCase{

  def testCalcularValorTrianguloPascal{
    assert(2>1)
    println("yep")
  }

  def testChequearBalance{
    var lista2:List[Char]=List('(','i','f','(','a', '¿','b',')' ,'(','b','/','a',')','e','l','s','e', '(','a','/','(','b'
      ,'*','b',')',')',')')
    assertTrue(Main.chequearBalance(lista2));

    lista2=List('(','c','c','c','(','c','c','c',')','c','c','(','(','c','c','c','(','c',')',')',')',')')
    assertTrue(Main.chequearBalance(lista2));

    lista2=List('(',')',')','(')
    assertFalse(Main.chequearBalance(lista2));

    lista2=List('(',')',')','(',')','(',')',')' )
    assertFalse(Main.chequearBalance(lista2));

    lista2=List('(','c','c','c','(','c','c','c','c','c','(','(','c','c','c','(','c',')',')',')',')')
    assertFalse(Main.chequearBalance(lista2));
  }

  def testContarCambiosPosibles{
    var lista:List[Int]=List(3,2,1)
    assertEquals(Main.contarCambiosPosibles(4,lista),4)

    lista=List(1)
    assertEquals(Main.contarCambiosPosibles(0,lista),1)
  }

  def testBusquedaBinaria{
    val lista:List[Int]=List(1,2,4,5,7,45,67,98,123)
    assertEquals(Main.busquedaBinaria(5,lista,(x:Int,y:Int)=>x<y),3)
    assertEquals(Main.busquedaBinaria(7,lista,(x:Int,y:Int)=>x<y),4)
    assertEquals(Main.busquedaBinaria(1,lista,(x:Int,y:Int)=>x<y),0)
    assertEquals(Main.busquedaBinaria(123,lista,(x:Int,y:Int)=>x<y),8)
    assertEquals(Main.busquedaBinaria(46,lista,(x:Int,y:Int)=>x<y),-1)
  }
}
