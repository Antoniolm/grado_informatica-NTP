/**
  * Created by LENOVO on 14/05/2017.
  */
class NodoInternoArbolHuffman(val hijoIzquierda:NodoArbolHuffman,val hijoDerecha:NodoArbolHuffman,
                              val elementos:List[Char] , val peso:Int) extends NodoArbolHuffman{

  val tipo="Interno"
  /**
    * Crea una cadena con el contenido del Nodo interno
    * @return
    */
  override def toString(): String = {
    val resultado= "[Nodo Interno]("+ elementos.toString()+","+peso/*+") ->\n{"+
      hijoIzquierda.toString+ ":::"+hijoDerecha.toString+"}"*/
    resultado
  }

  /**
    * Calcula el peso de un nodo interno a traves de
    * los pesos de sus nodos hijos
    * @return
  */
  override def calcularPeso(): Int = {
    hijoDerecha.calcularPeso+hijoIzquierda.calcularPeso
  }

  /**
    * obtener Caracteretes de un nodo interno a traves de
    * los caracteres de sus nodos hijos
    * @return
    */
  override def obtenerCaracteres:List[Char] ={
    hijoIzquierda.obtenerCaracteres ::: hijoDerecha.obtenerCaracteres
  }
}

object NodoInternoArbolHuffman {
  def apply(hI:NodoArbolHuffman,hD:NodoArbolHuffman,
            ele:List[Char] , p:Int)= new NodoInternoArbolHuffman(hI,hD,ele,p)
}
