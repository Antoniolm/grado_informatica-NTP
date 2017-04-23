/**
  * Created by LENOVO on 21/03/2017.
  * Los elementos static se van a implementar en objects
  * y las object tendran el mismo nombre que una clase
  * De un object solo puede haber una instancia
  * El main lo pondremos dentro de un object
  */
object Prueba7EstiloFuncional {
  def imprimirArgumentos(args : Array[String]) : Unit = {
    var i = 0

    while (i < args.length) {
      println(i)
      i += 1
    }
  }

  def functionalPrint(args : Array[String]) : Unit ={
    for(arg <- args) println(arg)

    args.foreach(println)
  }

  def functionalPrint2(args : Array[String]) : String ={
        args.mkString(" - ")
  }

  def main(args: Array[String]) : Unit ={
    //imprimirArgumentos(args)
    val cadena= functionalPrint2(args)
    println(cadena)
    assert(cadena== "Hola - Pepe - 23.8 - true")
  }

}
