/**
  * Created by LENOVO on 14/05/2017.
  */
class ArbolCodificacion(val cadena:String) {

  def calcularFrecuencia: List[(Char,Int)] ={
    var resultado:List[(Char, Int)] = List()

    var lastChar:Char = cadena(0)
    var cont:Int = 0

    for (elemento <- cadena){
        if(lastChar!=elemento) {
          resultado = resultado ::: List((lastChar, cont))
          lastChar = elemento
          cont = 0
        }
      cont+=1
    }

    resultado=resultado.sortBy(_._2)
    //println(resultado(0).toString())
    resultado
  }

  //Hacer singleton
  def unNodo(nodos:List[NodoArbolHuffman]) : Boolean ={
    if(nodos.length==1)
      true
    else
      false
  }

  def generarArbol():NodoArbolHuffman ={
    val resultado=calcularFrecuencia
    var nodeList:List[NodoArbolHuffman] = List()

    for(elemento <- resultado){
      nodeList = nodeList ::: List(NodoHojaArbolHuffman(elemento._1,elemento._2))
    }

    //while del metodo repetir
    while(!unNodo(nodeList)){
      nodeList = nodeList ::: List(NodoInternoArbolHuffman(nodeList(0),nodeList(1),
                              nodeList(0).obtenerCaracteres:::nodeList(1).obtenerCaracteres,nodeList(0).calcularPeso+nodeList(1).calcularPeso))
      nodeList=nodeList.tail
      nodeList=nodeList.tail
      nodeList.sortBy(_.peso)
    }
    nodeList.head
  }


}

object ArbolCodificacion {
  def apply(cadena:String ) = new ArbolCodificacion(cadena)

}

object Main extends App{
  val arbol=ArbolCodificacion("AAAAAAAABBBCDEFGH")
  val prueba=arbol.generarArbol()
  println(prueba.toString)

  val codigoHuffmanFrances: NodoArbolHuffman = NodoInternoArbolHuffman(
    NodoInternoArbolHuffman(
      NodoInternoArbolHuffman(
        NodoHojaArbolHuffman('s', 121895),
        NodoInternoArbolHuffman(
          NodoHojaArbolHuffman('d', 56269),
          NodoInternoArbolHuffman(
            NodoInternoArbolHuffman(
              NodoInternoArbolHuffman(
                NodoHojaArbolHuffman('x', 5928),
                NodoHojaArbolHuffman('j', 8351),
                List('x', 'j'), 14279),
              NodoHojaArbolHuffman('f', 16351),
              List('x', 'j', 'f'), 30630),
            NodoInternoArbolHuffman(
              NodoInternoArbolHuffman(
                NodoInternoArbolHuffman(
                  NodoInternoArbolHuffman(
                    NodoHojaArbolHuffman('z', 2093),
                    NodoInternoArbolHuffman(
                      NodoHojaArbolHuffman('k', 745),
                      NodoHojaArbolHuffman('w', 1747),
                      List('k', 'w'), 2492),
                    List('z', 'k', 'w'), 4585),
                  NodoHojaArbolHuffman('y', 4725),
                  List('z', 'k', 'w', 'y'), 9310),
                NodoHojaArbolHuffman('h', 11298),
                List('z', 'k', 'w', 'y', 'h'), 20608),
              NodoHojaArbolHuffman('q', 20889),
              List('z', 'k', 'w', 'y', 'h', 'q'), 41497),
            List('x', 'j', 'f', 'z', 'k', 'w', 'y', 'h', 'q'), 72127),
          List('d', 'x', 'j', 'f', 'z', 'k', 'w', 'y', 'h', 'q'), 128396),
        List('s', 'd', 'x', 'j', 'f', 'z', 'k', 'w', 'y', 'h', 'q'), 250291),
      NodoInternoArbolHuffman(
        NodoInternoArbolHuffman(
          NodoHojaArbolHuffman('o', 82762),
          NodoHojaArbolHuffman('l', 83668),
          List('o', 'l'), 166430),
        NodoInternoArbolHuffman(
          NodoInternoArbolHuffman(
            NodoHojaArbolHuffman('m', 45521),
            NodoHojaArbolHuffman('p', 46335),
            List('m', 'p'), 91856),
          NodoHojaArbolHuffman('u', 96785),
          List('m', 'p', 'u'),
          188641),
        List('o', 'l', 'm', 'p', 'u'), 355071),
      List('s', 'd', 'x', 'j', 'f', 'z', 'k', 'w', 'y', 'h', 'q', 'o', 'l', 'm', 'p', 'u'), 605362),
    NodoInternoArbolHuffman(
      NodoInternoArbolHuffman(
        NodoInternoArbolHuffman(
          NodoHojaArbolHuffman('r', 100500),
          NodoInternoArbolHuffman(
            NodoHojaArbolHuffman('c', 50003),
            NodoInternoArbolHuffman(
              NodoHojaArbolHuffman('v', 24975),
              NodoInternoArbolHuffman(
                NodoHojaArbolHuffman('g', 13288),
                NodoHojaArbolHuffman('b', 13822),
                List('g', 'b'), 27110),
              List('v', 'g', 'b'), 52085),
            List('c', 'v', 'g', 'b'), 102088),
          List('r', 'c', 'v', 'g', 'b'), 202588),
        NodoInternoArbolHuffman(
          NodoHojaArbolHuffman('n', 108812),
          NodoHojaArbolHuffman('t', 111103),
          List('n', 't'), 219915),
        List('r', 'c', 'v', 'g', 'b', 'n', 't'), 422503),
      NodoInternoArbolHuffman(
        NodoHojaArbolHuffman('e', 225947),
        NodoInternoArbolHuffman(
          NodoHojaArbolHuffman('i', 115465),
          NodoHojaArbolHuffman('a', 117110),
          List('i', 'a'), 232575),
        List('e', 'i', 'a'), 458522),
      List('r', 'c', 'v', 'g', 'b', 'n', 't', 'e', 'i', 'a'), 881025),
    List('s', 'd', 'x', 'j', 'f', 'z', 'k', 'w', 'y', 'h', 'q', 'o', 'l', 'm', 'p', 'u', 'r', 'c', 'v', 'g', 'b', 'n', 't', 'e', 'i', 'a'), 1486387)


  // Mensaje secreto a decodificar
  val mensajeSecretoFrances: List[Int] = List(0, 0, 1, 1, 1, 0, 1, 0, 1, 1, 1, 0, 0, 1, 1, 0, 1, 0,
    0, 1, 1, 0, 1, 0, 1, 1, 0, 0, 1, 1, 1, 1, 1, 0, 1, 0, 1, 1, 0, 0, 0, 0, 1, 0, 1, 1, 1,
    0, 0, 1, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 1)

  val conversor=Conversor(codigoHuffmanFrances)
  println(conversor.decodificar(mensajeSecretoFrances))
}
