/**
  * Created by LENOVO on 14/05/2017.
  */
class ArbolCodificacion(val cadena:String) {
  val raiz = Nil

  def calcularFrecuencia: List[(Char,Int)] ={
    var resultado:List[(Char, Int)] = List()

    var lastChar:Char = cadena(0)
    var cont:Int = 0

    for (elemento <- cadena){
        if(lastChar!=elemento) {
          resultado = resultado ::: List((lastChar, cont))
          lastChar = elemento
          cont = 0
        }
      cont+=1
    }

    resultado=resultado.sortBy(_._2)
    println(resultado(0).toString())
    resultado
  }

  //Hacer singleton
  def unNodo(nodos:List[NodoArbolHuffman]) : Boolean ={
    if(nodos.length==1)
      true
    else
      false
  }

  def generarArbol()={
    val resultado=calcularFrecuencia
    var nodeList:List[NodoArbolHuffman] = List()

    for(elemento <- resultado){
      nodeList = nodeList ::: List(NodoHojaArbolHuffman(elemento._1,elemento._2))
    }

    //while del metodo repetir
    while(unNodo(nodeList)){
      //nodeList = nodeList ::: List(NodoInternoArbolHuffman(nodeList(0),nodeList(1),......))
      nodeList=nodeList.tail
      nodeList=nodeList.tail
      nodeList.sortBy()
    }

  }


}

object ArbolCodificacion extends App{
  def apply(cadena:String ) = new ArbolCodificacion(cadena)

  val arbol=new ArbolCodificacion("AAAAAAAABBBCDEFGH")
  arbol.calcularFrecuencia
}
