/**
  * Created by LENOVO on 14/05/2017.
  */
class Decodificador(val raiz:NodoArbolHuffman,val cadena:List[Int]) {
  def decodificar():String = {

    def decodLetra(raiz:NodoArbolHuffman, cadena:List[Int]):String = {

      if(raiz.tipo=="Hoja")raiz.obtenerCaracteres(0)
      else {
        val actual=cadena.head
        val raizConv=raiz.asInstanceOf[NodoInternoArbolHuffman]

        if (actual == 0) decodLetra(raizConv.hijoIzquierda,cadena.tail)
        else decodLetra(raizConv.hijoDerecha,cadena.tail)
      }

    }
    decodLetra(raiz,cadena)

    /*def auxiliar(raiz:NodoArbolHuffman, cadena:List[Int],resultado:String):String = {

      if(cadena.size==0) resultado
      else {
        val actual=cadena.head
        if(raiz.tipo=="Hoja")
          resultado=resultado ::: (raiz.obtenerCaracteres)(0)
        else {
          if (actual == 0) auxiliar(raiz,cadena.tail,resultado)
          else auxiliar(raiz,cadena.tail,resultado)
        }
      }

    }
    var auxString=""
    auxiliar(raiz,cadena,new String);*/
  }
}

object Decodificador{
  def apply(raiz:NodoArbolHuffman,cadena:List[Int]) = new Decodificador(raiz,cadena)
}
