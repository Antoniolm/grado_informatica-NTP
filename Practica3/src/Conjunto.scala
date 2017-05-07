/**
* Clase para representar conjuntos definidos mediante una funcion
* caracteristica (un predicado). De esta forma, se declara el tipo
* conjunto como un predicado que recibe un entero (elemento) como
* argumento y dvuelve un valor booleano que indica si pertenece o no
* al conjunto
*
* @param funcionCaracteristica
*/
class Conjunto(val funcionCaracteristica: Int => Boolean) {
  /**
  * Crea una cadena con el contenido completo del conjunto
  *
  * @return
  */
  override def toString(): String = {
    val elementos =
      for (i <- -Conjunto.LIMITE to Conjunto.LIMITE
      if funcionCaracteristica(i)) yield i
        elementos.mkString("{", ",", "}")
  }

  /**
  * Metodo para determinar la pertenencia de un elemento al
  * conjunto
  * @param elemento
  * @return valor booleano indicando si elemento cumple
  * la funcion caracteristica o no
  */
  def apply(elemento: Int): Boolean = {
    funcionCaracteristica(elemento)
    }
  }

/**
* Objecto companion que ofrece metodos para trabajar con
* conjuntos
*/
object Conjunto{
  /**
  * Limite para la iteracion necesaria algunas operaciones,
  * entre -1000 y 1000
  */
  private final val LIMITE = 1000

  /**
  * Metodo que permite crear objetos de la clase Conjunto
  * de forma sencilla
  * @param f
  * @return
  */
  def apply(f: Int => Boolean): Conjunto = {
    new Conjunto(f)
  }

  def conjuntoUnElemento(elemento : Int) : Conjunto ={
    Conjunto((x:Int) => x==elemento)
  }

  def union(c1 : Conjunto, c2 : Conjunto) : Conjunto = {
    Conjunto((x:Int) => c1.funcionCaracteristica(x) || c2.funcionCaracteristica(x))
  }

  def interseccion(c1 : Conjunto, c2 : Conjunto) : Conjunto = {
    Conjunto((x:Int) => c1.funcionCaracteristica(x) & c2.funcionCaracteristica(x))
  }

  def diferencia(c1 : Conjunto, c2 : Conjunto) : Conjunto = {
    Conjunto((x:Int) => c1.funcionCaracteristica(x) & !c2.funcionCaracteristica(x))
  }

  def filtrar(c : Conjunto, predicado : Int => Boolean) : Conjunto ={
    Conjunto((x:Int) => c.funcionCaracteristica(x) & predicado(x))
  }

  def paraTodo(conjunto : Conjunto, predicado : Int => Boolean) : Boolean = {
    def iterar(elemento : Int) : Boolean = {
      if( !predicado(elemento) & conjunto.funcionCaracteristica(elemento)) false
      else if (elemento==LIMITE) true
      else iterar(elemento+1)
    }
    iterar(-LIMITE)
  }

  def existe(c : Conjunto, predicado : Int => Boolean) : Boolean ={
    var result=false
    for (i <- -Conjunto.LIMITE to Conjunto.LIMITE ) {
      if (paraTodo(conjuntoUnElemento(i), (x:Int) => c.funcionCaracteristica(x) & predicado(x)))
        result=true
    }

    result
    /*def iterar(elemento : Int) : Boolean = {
      if( predicado(elemento) & c.funcionCaracteristica(elemento)) true
      else if (elemento==LIMITE) false
      else iterar(elemento+1)
    }
    iterar(-LIMITE)*/
  }

  def map(c : Conjunto, funcion : Int => Int) : Conjunto = {
    Conjunto((x:Int) => existe(c,(y:Int) => funcion(y)==x))
  }


}