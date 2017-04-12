
/**
  * Objeto singleton para probar la funcionalidad del triangulo
  * de Pascal
  */
object Main {

  /**
    * Metodo main: en realidad no es necesario porque el desarrollo
    * deberia guiarse por los tests de prueba
    *
    * @param args
    */

  def main(args: Array[String]) {
    println("................... Triangulo de Pascal ...................")

    // Se muestran 10 filas del trinagulo de Pascal
    for (row <- 0 to 10) {
      // Se muestran 10 10 columnas
      for (col <- 0 to row)
        print(calcularValorTrianguloPascal(col, row) + " ")

      // Salto de linea final para mejorar la presentacion
      println()
    }

    // Se muestra el valor que debe ocupar la columna 5 en la fila 10
    println(calcularValorTrianguloPascal(10, 15))
    println(calcularValorTrianguloPascal(0, 0))

    val lista:List[Int]=List(1,2,3)
    println("Resultado="+contarCambiosPosibles(4,lista))

    //val lista2:List[Char]=List('(','i','f','(','a', '¿','b',')' ,'(','b','/','a',')','e','l','s','e', '(','a','/','(','b'
    //  ,'*','b',')',')',')')
    //val lista2:List[Char]=List('(','c','c','c','(','c','c','c',')','c','c','(','(','c','c','c','(','c',')',')',')',')')

    //val lista2:List[Char]=List('(',')',')','(')
    //val lista2:List[Char]=List('(',')',')','(',')','(',')',')' )
    val lista2:List[Char]=List('(','c','c','c','(','c','c','c','c','c','(','(','c','c','c','(','c',')',')',')',')')
    println("Resultado="+chequearBalance(0,lista2))

    val lista3:List[Int]=List(1,2,4,5,7,45,67,98,123)
    println("Resultado="+busquedaBinaria(5,lista3))
  }

  /**
    * Ejercicio 1: funcion para generar el triangulo de Pascal
    *
    * @param columna
    * @param fila
    * @return
    */
  def calcularValorTrianguloPascal(columna: Int, fila: Int): Int = {
    if(columna ==0 || fila==columna) 1
    else calcularValorTrianguloPascal(columna,fila-1)+calcularValorTrianguloPascal(columna-1,fila-1)
  }

  /**
    * Ejercicio 2: funcion para chequear el balance de parentesis
    *
    * @param cadena cadena a analizar
    * @return valor booleano con el resultado de la operacion
    */
  def chequearBalance(cant:Int,cadena: List[Char]): Boolean = {
    if(cadena.isEmpty && cant==0) true
    else if(cadena.isEmpty && cant>0) false
    else if(cant<0)false
    else {
      val charact=cadena.head
      if(charact=='(') chequearBalance(cant+1,cadena.tail)
      else if(charact==')') chequearBalance(cant-1,cadena.tail)
      else chequearBalance(cant,cadena.tail)
    }
  }

  /**
    * Ejercicio 3: funcion para determinar las posibles formas de devolver el
    * cambio de una determinada cantidad con un conjunto de monedas
    *
    * @param cantidad
    * @param monedas
    * @return contador de numero de vueltas posibles
    */
  def contarCambiosPosibles(cantidad: Int, monedas: List[Int]): Int = {
     var i=0
    var result=0

    if(cantidad==0) 1
    else if(cantidad < 0) 0
    else {
      while(i<monedas.length && cantidad-monedas(i)>=0) {
        result=result+contarCambiosPosibles(cantidad-monedas(i), monedas)
        i=i+1
      }
      result
    }
  }
  
  /**
    * Ejercicio 4: Busqueda binaria generica
    * @param elem
    * @param lista
    * @return
    */
  def busquedaBinaria(elem: Int, lista: List[Int]): Int = {

      @annotation.tailrec
      def auxiliar(elem: Int, lista: List[Int], infLimit: Int, posActual: Int, supLimit: Int):Int ={
        if (infLimit>supLimit) -1
        else if ( elem == lista(posActual) ) posActual
        else if ( elem < lista(posActual) ) auxiliar(elem, lista, infLimit, ((infLimit+posActual-1)/2), (posActual-1))
        else auxiliar(elem, lista, (posActual+1), ((supLimit+posActual+1)/2), supLimit)
      }

      auxiliar(elem, lista, 0, lista.length/2, lista.length)
    }
}
