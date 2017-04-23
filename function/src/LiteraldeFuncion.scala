/**
  * Created by LENOVO on 03/04/2017.
  */
object LiteraldeFuncion {

  val multiplicarPor2 = (x: Int) => x * 2

  def calcularMaximo(a: Int, b: Int) = if (a > b) a else b

  val variableFuncion: (Int, Int) => Int = calcularMaximo

  //Con la expresión no tenemos que indicar el tipo como en el
  //caso de arriba que al declarar el metodo debemos indica el tipo
  //de devolución en nuestra variableFuncion
  val variableFunction2 = (a:Int,b:Int) => if(a>b) a else b


}
