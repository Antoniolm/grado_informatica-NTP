/**
  * Created by LENOVO on 14/05/2017.
  */
type TablaCodigo=List[(Char, List[Int])]


class Conversor(val raiz:NodoArbolHuffman) {


  def decodificar(cadena:List[Int]):String = {

    def auxiliar(nodos:NodoArbolHuffman, cadena:List[Int],resultado:String):String = {

      if(cadena.isEmpty) resultado + nodos.obtenerCaracteres(0)
      else {
        val actual=cadena.head
        if(nodos.tipo=="Hoja") {
          auxiliar(raiz,cadena,resultado + nodos.obtenerCaracteres(0))
        }
        else {
          val nodoConv=nodos.asInstanceOf[NodoInternoArbolHuffman]

          if (actual == 0) auxiliar(nodoConv.hijoIzquierda,cadena.tail,resultado)
          else auxiliar(nodoConv.hijoDerecha,cadena.tail,resultado)
        }
      }

    }

    auxiliar(raiz,cadena,new String)
  }


}

object Conversor{
  def apply(raiz:NodoArbolHuffman) = new Conversor(raiz)
}
