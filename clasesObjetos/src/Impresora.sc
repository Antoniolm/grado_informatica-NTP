class Impresora(mensaje:String){
 def imprimir(cadena:String):Unit={
   println(s"$mensaje: $cadena")
 }
  def imprimir(cadenas:Seq[String]):Unit={
    println(mensaje,cadenas.mkString("\n"))
  }
}

val objeto=new Impresora("epson ml200")
objeto.imprimir("documento corto")
objeto.imprimir("linea1":: "linea2"::"linea3"::Nil)

//Usode metodo apply
class Multiplicador(factor:Int){
  def apply(valor:Int)=valor*factor

}

val porTres=new Multiplicador(3)
porTres.apply(10)
val res2=porTres(8)


