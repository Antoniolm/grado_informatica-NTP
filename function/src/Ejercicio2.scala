/**
  * Created by LENOVO on 04/04/2017.
  */
object Ejercicio2 extends App{

  def fibonacci(n:Int): Int ={
    if(n==0 || n==1) n
    else fibonacci(n-1)+fibonacci(n-2)
  }
  val result=fibonacci(9)
  println(result)

  def fibonacciTailRec(n:Int): Int ={
    @annotation.tailrec
    def iterar(n:Int,act:Int,sig:Int):Int ={
      if(n==0) act
      else iterar(n-1,sig,act+sig)
    }
    iterar(n,0,1)
  }

  val result1=fibonacciTailRec(9)
  println("Prueba ->"+result1)

  def ordenado[A](array:Array[A],comparar:(A,A)=>Boolean):Boolean={
    def iterar(indice:Int): Boolean ={
      //Caso base 1: no pasarse de longitud
      if(indice==array.length-2) comparar(array(indice),array(indice+1))

      // Caso base 2: que los elementos a comparar no esten ordenados
      else if(!comparar(array(indice),array(indice+1))) false
      else iterar(indice+1)
    }
    iterar(0)
  }

  val array1:Array[Int]=Array(1,5,29,35,57,98,123,215)
  Ejercicio2.ordenado(array1,(x:Int,y:Int)=> x<y)

  @annotation.tailrec
  def ordenadoAlternativo[A](array:Array[A],comparar:(A,A)=>Boolean):Boolean={
    //Caso base : array de 1 elemento
    if(array.length==1) true
    else if(!comparar(array(0),array(1)))false
    else ordenadoAlternativo(array.tail,comparar)
  }
}
