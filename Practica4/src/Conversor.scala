/**
  * Created by LENOVO on 14/05/2017.
  */



class Conversor(val raiz:NodoArbolHuffman) {
  type TablaCodigo = List[(Char, List[Int])]

  /**
    * Metodo que decodifica una cadena
    *
    * @param cadena
    * @return
    */
  def decodificar(cadena: List[Int]): String = {

    def auxiliar(nodos: NodoArbolHuffman, cadena: List[Int], resultado: String): String = {

      if (cadena.isEmpty) resultado + nodos.obtenerCaracteres(0)
      else {
        val actual = cadena.head
        if (nodos.tipo == "Hoja") {
          auxiliar(raiz, cadena, resultado + nodos.obtenerCaracteres(0))
        }
        else {
          val nodoConv = nodos.asInstanceOf[NodoInternoArbolHuffman]

          if (actual == 0) auxiliar(nodoConv.hijoIzquierda, cadena.tail, resultado)
          else auxiliar(nodoConv.hijoDerecha, cadena.tail, resultado)
        }
      }

    }

    auxiliar(raiz, cadena, new String)
  }


  /**
    * Convierte el arbol de huffman en
    * una tabla de codigos ( List[(Char, List[Int] )
    *
    * @param arbolCodificacion
    * @return
    */
  def convertirArbolTabla(arbolCodificacion: NodoArbolHuffman): TablaCodigo = {
    def auxiliar(nodo: NodoArbolHuffman, tabla: List[Int], resultado: TablaCodigo): TablaCodigo = {
      if (nodo.tipo == "Hoja") {
        resultado ::: List((nodo.obtenerCaracteres(0), tabla))
      }
      else {
        val nodoConv = nodo.asInstanceOf[NodoInternoArbolHuffman]

        auxiliar(nodoConv.hijoIzquierda, tabla ::: List(0), resultado) :::
          auxiliar(nodoConv.hijoDerecha, tabla ::: List(1), resultado)

      }
    }

    auxiliar(arbolCodificacion, List(), List())
  }

  /**
    * Metodo que me devuelve la ruta (01101) de un caracter
    *
    * @param tabla
    * @param caracter
    * @return
    */
  def codificarConTabla(tabla: TablaCodigo)(caracter: Char): List[Int] = {
    for (elemento <- tabla) {
      if (elemento._1 == caracter) {
        return elemento._2
      }
    }
    return Nil
  }

  /**
    * Codificación Rapida utilizando la tabla de codigos
    *
    * @param tabla
    * @param cadena
    * @return
    */
  def codificaciónRapida(tabla: TablaCodigo, cadena: List[Char]): List[Int] = {

    def auxiliar(cad: List[Char], resultado: List[Int]): List[Int] = {
      if (cad.isEmpty) resultado
      else {
        val elemento = cad.head
        auxiliar(cad.tail, resultado ::: codificarConTabla(tabla)(elemento))
      }
    }

    auxiliar(cadena, List())
  }

  /**
    * Codificación sin utilizar la tabla de códigos
    *
    * @param cadena
    * @return
    */
  def codificación(nodos: NodoArbolHuffman, cadena: List[Char]): List[Int] = {

    def auxiliar(nodos: NodoArbolHuffman, cadena: List[Char], resultado: List[Int], currentCod: List[Int]): List[Int] = {

      if (cadena.isEmpty || (nodos.tipo == "Hoja" && nodos.obtenerCaracteres(0) != cadena.head)) resultado
      else {
        val actual = cadena.head
        if (nodos.tipo == "Hoja" && nodos.obtenerCaracteres(0) == actual) {
          auxiliar(raiz, cadena.tail, resultado ::: currentCod, List())
        }
        else {
          val nodoConv = nodos.asInstanceOf[NodoInternoArbolHuffman]

          auxiliar(nodoConv.hijoIzquierda, cadena, resultado, currentCod ::: List(0)):::
          auxiliar(nodoConv.hijoDerecha, cadena, resultado, currentCod ::: List(1))
        }
      }

    }

    auxiliar(raiz, cadena, List(), List())
  }
}

object Conversor{
  def apply(raiz:NodoArbolHuffman) = new Conversor(raiz)
}
