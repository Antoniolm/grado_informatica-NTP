/**
  * Created by LENOVO on 14/05/2017.
  */
class NodoInternoArbolHuffman(val hijoDerecha:NodoArbolHuffman,val hijoIzquierda:NodoArbolHuffman,
                              val elementos:List[Char] , val peso:Int) extends NodoArbolHuffman{

  val tipo="Interno"
  /**
    * Crea una cadena con el contenido del Nodo interno
    * @return
    */
  override def toString(): String = {
    val resultado= "[Nodo Interno]("+ elementos.toString()+","+peso+") ->\n{"+
      hijoDerecha.toString+ ":::"+hijoIzquierda.toString+"}"
    resultado
  }

  override def calcularPeso(): Int = {
    hijoDerecha.calcularPeso+hijoIzquierda.calcularPeso
  }

  override def obtenerCaracteres:List[Char] ={
    hijoDerecha.obtenerCaracteres ::: hijoIzquierda.obtenerCaracteres
  }
}


object NodoInternoArbolHuffman {
  def apply(hD:NodoArbolHuffman,hI:NodoArbolHuffman,
            ele:List[Char] , p:Int)= new NodoInternoArbolHuffman(hD,hI,ele,p)
}
