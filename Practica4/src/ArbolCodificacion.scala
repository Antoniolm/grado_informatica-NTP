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
    var resultado= scala.collection.mutable.Map[Char, Int]()

    for(elemento <- cadena){
          if (resultado.contains(elemento)) {
            resultado(elemento) = resultado(elemento) + 1
          }
          else {
            resultado(elemento) = 1
          }
    }
    var resultado2  =scala.collection.immutable.TreeMap(resultado.toArray:_*)
    resultado2.toList.sortWith(_._2 < _._2)
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

    println(nodeList.toString())
    while(!unNodo(nodeList)){
      nodeList = nodeList ::: List(NodoInternoArbolHuffman(nodeList(0),nodeList(1),
                              nodeList(0).obtenerCaracteres:::nodeList(1).obtenerCaracteres,nodeList(0).calcularPeso+nodeList(1).calcularPeso))
      nodeList=nodeList.tail
      nodeList=nodeList.tail
      nodeList=nodeList.sortWith(_.peso < _.peso)
      println(nodeList.toString())
    }
    nodeList.head
  }


}

object ArbolCodificacion {
  def apply(cadena:String ) = new ArbolCodificacion(cadena)
}
