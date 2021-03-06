
/**
  * Created by Antonio David López Machado on 18/04/2017.
  */


/**
  * Objeto singleton para probar la funcionalidad del triangulo
  * de Pascal
  */

object Main {
  /**
    * Ejercicio 1: funcion para generar el triangulo de Pascal
    *
    * @param columna
    * @param fila
    * @return
    */
  def calcularValorTrianguloPascal(columna: Int, fila: Int): Int = {
    //Caso base : no pasarse de longitud en la columna
    if(columna ==0 || fila==columna) 1
    else calcularValorTrianguloPascal(columna,fila-1)+calcularValorTrianguloPascal(columna-1,fila-1)
  }

  /**
    * Ejercicio 2: funcion para chequear el balance de parentesis
    *
    * @param cadena cadena a analizar
    * @return valor booleano con el resultado de la operacion
    */
  def chequearBalance(cadena: List[Char]): Boolean = {

    def auxiliar(cant:Int,cadena: List[Char]): Boolean = {
      //Caso base 1 : si la cadena esta vacia y no hay parentesis sin su cierre
      if (cadena.isEmpty && cant == 0) true

      //Caso base 2: si la cadena esta vacia pero hay parentesis sin su cierre
      else if (cadena.isEmpty && cant > 0) false

      //Caso base 3: hay parentesis sin cerrar antes terminar de procesar la cadena
      else if (cant < 0) false

      else {
        val charact = cadena.head
        if (charact == '(') auxiliar(cant + 1, cadena.tail)
        else if (charact == ')') auxiliar(cant - 1, cadena.tail)
        else auxiliar(cant, cadena.tail)
      }
    }
    auxiliar(0,cadena);
  }

  /**
    * Ejercicio 3: funcion para determinar las posibles formas de devolver el
    * cambio de una determinada cantidad con un conjunto de monedas
    *
    * @param cantidad cantidad a devolver
    * @param monedas lista de monedas disponibles
    * @return contador de numero de vueltas posibles
    */
  def contarCambiosPosibles(cantidad: Int, monedas: List[Int]): Int = {
    var result=0

    //Caso base 1: Si el cambio es correcto y la cantidad ha llegado a cero
    if(cantidad==0) 1

    //Caso base 2: si se han procesado todas las monedas y el cambio no es correcto
    else if(monedas.isEmpty || cantidad < 0) 0

    else {
      val moneda=monedas.head
      contarCambiosPosibles(cantidad-moneda, monedas)+contarCambiosPosibles(cantidad, monedas.tail)
    }

  }


  /**
    * Ejercicio 4: función para determinar la posición de un elemento
    * en un conjunto(si existiera dicho elemento). Se realiza el tipo de busqueda
    * binaria.
    * @param elem elemento a buscar
    * @param lista lista ordenada donde se realizara la busqueda
    * @return posición del elemento en la lista ( si no esta -1)
    */
  def busquedaBinaria[A](elem: A, lista: List[A],comparar:(A,A)=>Boolean): Int = {

      @annotation.tailrec
      def auxiliar(elem: A, lista: List[A], infLimit: Int, posActual: Int, supLimit: Int):Int ={
        //Caso base 1: no hay lista o no se ha encontrado el elemento
        if (infLimit>supLimit || lista.isEmpty) -1
        //Caso base 2: Se ha encontrado el elemento
        else if ( elem == lista(posActual) ) posActual

        else if ( comparar(elem,lista(posActual)) ) auxiliar(elem, lista, infLimit, ((infLimit+posActual-1)/2), (posActual-1))
        else auxiliar(elem, lista, (posActual+1), ((supLimit+posActual+1)/2), supLimit)
      }

      auxiliar(elem, lista, 0, lista.length/2, lista.length)

  }
}
