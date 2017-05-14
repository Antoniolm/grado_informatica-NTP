/**
  * Created by LENOVO on 14/05/2017.
  */
class Decodificador(val raiz:NodoArbolHuffman,val cadena:List[Int]) {
  def decodificar():String = {

    /*def decodLetra(raiz:NodoArbolHuffman, cadena:List[Int]):Char = {

      if(raiz.tipo=="Hoja")raiz.obtenerCaracteres(0)
      else {
        val actual=cadena.head
        println("Elemento-->"+actual)
        val raizConv=raiz.asInstanceOf[NodoInternoArbolHuffman]

        if (actual == 1) decodLetra(raizConv.hijoIzquierda,cadena.tail)
        else decodLetra(raizConv.hijoDerecha,cadena.tail)
      }

    }
    decodLetra(raiz,cadena)*/

    def auxiliar(nodos:NodoArbolHuffman, cadena:List[Int],resultado:String):String = {

      if(cadena.isEmpty) resultado
      else {
        val actual=cadena.head
        if(nodos.tipo=="Hoja") {
          auxiliar(raiz,cadena,resultado + nodos.obtenerCaracteres(0).toString)
        }
        else {
          val nodoConv=nodos.asInstanceOf[NodoInternoArbolHuffman]

          if (actual == 1) auxiliar(nodoConv.hijoIzquierda,cadena.tail,resultado)
          else auxiliar(nodoConv.hijoDerecha,cadena.tail,resultado)
        }
      }

    }
    var auxString=""
    auxiliar(raiz,cadena,new String)
  }
}

object Decodificador{
  def apply(raiz:NodoArbolHuffman,cadena:List[Int]) = new Decodificador(raiz,cadena)
}
