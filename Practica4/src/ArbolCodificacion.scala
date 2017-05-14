/**
  * Created by LENOVO on 14/05/2017.
  */
class ArbolCodificacion(val cadena:String) {

  def calcularFrecuencia: List[(Char,Int)] ={
    var resultado:List[(Char, Int)] = List()

    var lastChar:Char = cadena(0)
    var cont:Int = 0

    for (elemento <- cadena){
        if(lastChar!=elemento) {
          resultado = resultado ::: List((lastChar, cont))
          println("N- "+cont+ " - "+ lastChar)
          lastChar = elemento
          cont = 0
        }
      cont+=1
    }

    resultado
  }

}

object ArbolCodificacion extends App{
  val arbol=new ArbolCodificacion("AAAAAAAABBBCDEFGH")
  arbol.calcularFrecuencia
}
