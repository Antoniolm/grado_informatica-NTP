/**
  * Created by LENOVO on 03/04/2017.
  */
object FuncionesOrdenSuperior extends App{

  /**
    * Operación segura con cadenas para comprobar si la cadena es nula
    *
    * @param cadena
    * @param operacion
    * @return
    */
  def operacionSeguraString(cadena : String , operacion : String => String ) = {
      if(cadena== null) cadena
      else operacion(cadena)
  }

  def invertir(cadena : String)=cadena.reverse

  def aMayus(cadena:String)= cadena.toUpperCase

  val resultado1=operacionSeguraString("Hola", invertir)
  val resultado2=operacionSeguraString("Hola", aMayus)
  val cadenaNula:String=null
  val resultado3=operacionSeguraString(cadenaNula, invertir)

  //Operaciones basicas
  def mas5(x:Double) = x+5
  def cuadrado(x:Double)= x*x

  def aComponer(f:Double => Double,g:Double => Double) : Double => Double ={
    x => f(g(x))
  }

  val fg: (Double) => Double =aComponer(mas5,cuadrado)
  fg(5)

  //Otra forma de llamarlo seria
  val resultado=aComponer(mas5,cuadrado)(5)


}
