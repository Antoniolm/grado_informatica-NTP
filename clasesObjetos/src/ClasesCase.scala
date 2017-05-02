
//nombre y heroe son datos mienbro tipo val
case class Personaje(nombre:String, heroe:Boolean)

object ClasesCase extends App{
  val personaje1=Personaje("Gollum",false)
  val personaje2=Personaje("Gandalf",true)

  //Gracias puedo usar equals( == )
  val comparacion=personaje1 == personaje2
  println(comparacion)

  //podemos usar toString
  println(personaje1)

  //uedo copiar modificando algunos datos mienrbos
  val personaje3=personaje1.copy("Sauron")

  //uso de unapply
  val resultado=Personaje.unapply(personaje1)
  println(resultado)
}
