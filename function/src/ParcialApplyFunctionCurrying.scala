/**
  * Created by LENOVO on 03/04/2017.
  */
object ParcialApplyFunctionCurrying extends App{
  def divisible(x:Int, y:Int) = x%y ==0

  //Uso de marcador de posicion para funcion aplicada
  //parcialmente
  val f=divisible _

  val resultado= f(20,3)

  val divisiblePor3: (Int)=> Boolean = divisible(_:Int,3)
  val resultado2=divisiblePor3(21)

  //Mas natural con dos listas de argumentos
  def divisiblePor(x:Int)(y:Int)= x%y==0

  val divisiblePor2= divisiblePor(_:Int)(2)

}
