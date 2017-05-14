/**
  * Created by LENOVO on 14/05/2017.
  */
abstract class NodoArbolHuffman {

}

class NodoInternoArbolHuffman(val hijoDerecha:NodoArbolHuffman,val hijoIzquierda:NodoArbolHuffman,
                              val elementos:List[String] , val peso:Int) extends NodoArbolHuffman{

}

class NodoHojaArbolHuffman(val elemento:Char , val peso:Int) extends NodoArbolHuffman{
  
}