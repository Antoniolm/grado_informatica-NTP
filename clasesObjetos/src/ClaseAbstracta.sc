abstract class Coche {
  val fechaCompra:Int
  val automatico:Boolean=true
  def color:String
}

class Mini(
  val fechaCompra:Int, val color:String
  ) extends Coche

val c2:Coche=new Mini(2015,"rojo")

class Ejemplo(nombre:String){
  def mostrar():Unit = {
    println("En clase Ejemplo")
  }
  println("En el cuerpo del a clase")
  println("Nombre :"+nombre)
}

val c3=new Ejemplo("Pepe")

abstract class Listener {
  def trigger
}

val miListener= new Listener{
  def trigger:Unit = {
    println(s"Activacion ${new java.util.Date}")
  }

}
miListener.trigger

miListener.trigger

val otroListener=new Listener{
  def trigger:Unit={
    println("Disparo de metodo en clase otroListener")
  }
}

otroListener.trigger