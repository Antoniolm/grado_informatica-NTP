/**
  * Created by LENOVO on 14/05/2017.
  */



class Conversor(val raiz:NodoArbolHuffman) {
  type TablaCodigo=List[(Char, List[Int])]

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

  def convertirArbolTabla(arbolCodificacion : NodoArbolHuffman) : TablaCodigo ={
      def auxiliar(nodo:NodoArbolHuffman,tabla:List[Int],resultado:TablaCodigo): TablaCodigo = {
        if(nodo.tipo=="Hoja"){
          resultado ::: List((nodo.obtenerCaracteres(0),tabla))
        }
        else{
          val nodoConv=nodo.asInstanceOf[NodoInternoArbolHuffman]

          auxiliar(nodoConv.hijoIzquierda,tabla ::: List(0),resultado) :::
            auxiliar(nodoConv.hijoDerecha,tabla ::: List(1),resultado)

        }
      }
      auxiliar(arbolCodificacion,List(),List())
  }
}

object Conversor{
  def apply(raiz:NodoArbolHuffman) = new Conversor(raiz)
}