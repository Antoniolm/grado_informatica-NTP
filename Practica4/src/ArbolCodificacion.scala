/**
  * Created by LENOVO on 14/05/2017.
  */
class ArbolCodificacion(val cadena:String) {

  /**
    * Metodo que nos permite calcular la frecuencia de uso de
    * una cadena ordenada de elementos
    * @return
    */
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
    resultado = resultado ::: List((lastChar, cont))
    resultado=resultado.sortBy(_._2)
    resultado
  }

  /**
    * Metodo que nos indica si una lista de nodos contiene
    * solo un nodo
    * @param nodos
    * @return
    */
  def unNodo(nodos:List[NodoArbolHuffman]) : Boolean ={
    if(nodos.length==1)
      true
    else
      false
  }

  /**
    * Nos permite generar un arbol huffman a partir de una cadena de caracteres
    * @return
    */
  def generarArbol():NodoArbolHuffman ={
    val resultado=calcularFrecuencia
    var nodeList:List[NodoArbolHuffman] = List()

    for(elemento <- resultado){
      nodeList = nodeList ::: List(NodoHojaArbolHuffman(elemento._1,elemento._2))
    }
    
    while(!unNodo(nodeList)){
      nodeList = nodeList ::: List(NodoInternoArbolHuffman(nodeList(0),nodeList(1),
                              nodeList(0).obtenerCaracteres:::nodeList(1).obtenerCaracteres,nodeList(0).calcularPeso+nodeList(1).calcularPeso))
      nodeList=nodeList.tail
      nodeList=nodeList.tail
      nodeList=nodeList.sortBy(_.peso)
    }
    nodeList.head
  }


}

object ArbolCodificacion {
  def apply(cadena:String ) = new ArbolCodificacion(cadena)
}
