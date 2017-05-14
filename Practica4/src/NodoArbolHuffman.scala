/**
  * Created by LENOVO on 14/05/2017.
  */
abstract class NodoArbolHuffman {

}

/////////////////////////////////////////////////

class NodoInternoArbolHuffman(val hijoDerecha:NodoArbolHuffman,val hijoIzquierda:NodoArbolHuffman,
                              val elementos:List[String] , val peso:Int) extends NodoArbolHuffman{
  /**
    * Crea una cadena con el contenido del Nodo interno
    * @return
    */
  override def toString(): String = {
    val resultado= "[Nodo Interno] Elementos ->"+ elementos.toString()+", peso -> "+peso
    resultado
  }
}

object NodoInternoArbolHuffman {
  def apply(hD:NodoArbolHuffman,hI:NodoArbolHuffman,
            ele:List[String] , p:Int)= new NodoInternoArbolHuffman(hD,hI,ele,p)
}

//////////////////////////////////////////////////////

class NodoHojaArbolHuffman(val elemento:Char , val peso:Int) extends NodoArbolHuffman{
  /**
    * Crea una cadena con el contenido del Nodo hoja
    * @return
    */
  override def toString(): String = {
    val resultado= "[Nodo Hoja] Elemento ->"+ elemento+", peso -> "+peso
    resultado
  }
}

object NodoHojaArbolHuffman {
  def apply(ele:Char , p:Int)= new NodoHojaArbolHuffman(ele,p)
}