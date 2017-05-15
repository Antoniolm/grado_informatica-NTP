/**
  * Created by LENOVO on 14/05/2017.
  */
class NodoHojaArbolHuffman(val elemento:Char , val peso:Int) extends NodoArbolHuffman{
  val tipo="Hoja"

  /**
    * Crea una cadena con el contenido del Nodo hoja
    * @return
    */
  override def toString(): String = {
    val resultado= "[Nodo Hoja] ("+ elemento+","+peso+")"
    resultado
  }

  /**
    * Calcular el peso de nuestro nodo hoja
    * @return
    */
  override def calcularPeso(): Int = {
    peso
  }

  /**
    * Devuelve el caracter de nuestro nodo hoja
    * @return
    */
  override def obtenerCaracteres:List[Char] ={
    List(elemento)
  }
}


object NodoHojaArbolHuffman {
  def apply(ele:Char , p:Int)= new NodoHojaArbolHuffman(ele,p)
}