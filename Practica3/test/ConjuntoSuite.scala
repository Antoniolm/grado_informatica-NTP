

/**
  * Created by Antonio David López Machado on 18/04/2017.
  */

import junit.framework.TestCase
import org.junit.Assert._

class ConjuntoSuite extends TestCase {

  // Se importan las declaraciones  en ConjuntoFuncional


  // Se crea un trait incluyendo tres conjuntos, que se
  // usan en cada test
  trait TestSets {
    val s1 = Conjunto.conjuntoUnElemento(1)
    val s2 = Conjunto.conjuntoUnElemento(2)
    val s3 = Conjunto.conjuntoUnElemento(3)
  }

  /**
    * Se comprueba que el elemento 1 esta contenido en s1
    */
  def testConjuntosUnElemento() {
    // Se crea instancia de los conjuntos
    new TestSets {
      // Si falla el assert se muestra el mensaje de error
      // que aparece como segundo argumento
      assert(s1(1), "fallo: s1 no contiene a 1")
    }
  }

  /**
    * Test for union
    */
  def testUnion() {
    new TestSets {
      val s = Conjunto.union(s1, s2)
      assert(s(1), "fallo: s no contiene a 1")
      assert(s(2), "fallo: s no contiene a 2")
      assert(!s(3), "fallo: s contiene a 3")

    }
  }

  /**
    * Otro test para la union
    */
  def testUnion2() {
    val conjunto1 = Conjunto((x: Int) => x > 3)
    val conjunto2 = Conjunto((x: Int) => x > 5)

    // Conjunto union: enteros mayores de 3
    val conjuntoUnion:Conjunto = Conjunto.union(conjunto1, conjunto2)

    // 4, 5, 6 y 7  pertenecen a la union
    assert(conjuntoUnion(4))
    assert(conjuntoUnion(5))
    assert(conjuntoUnion(6))
    assert(conjuntoUnion(7))

    // 3 y 0 no pertenecen a la union
    assert(!conjuntoUnion(3))
    assert(!conjuntoUnion(0))
  }

  /**
    * Test para interseccion
    */
  def testInterseccion() {
    val conjunto1 = Conjunto((x: Int) => x > 3)
    val conjunto2 = Conjunto((x: Int) => x > 5)

    // Formacion de la interseccion: solo a partir de 5
    val conjuntoInterseccion = Conjunto.interseccion(conjunto1, conjunto2)

    // 6 pertenece
    assert(conjuntoInterseccion(6))

    // no 4 ni 5 pertenecen
    assert(!conjuntoInterseccion(4))
    assert(!conjuntoInterseccion(5))
  }

  /**
    * Test de diferencia
    */
  def testDiferencia() {
    val conjunto1 = Conjunto((x: Int) => x > 3)
    val conjunto2 = Conjunto((x: Int) => x < 10)

    // Diferencia: mayores de 3 pero no menores de 10
    val conjuntoDiferencia = Conjunto.diferencia(conjunto1, conjunto2)

    // 6 no pertenece y 11 si
    assert(!conjuntoDiferencia(6))
    assert(conjuntoDiferencia(11))
  }

  /**
    * Test de filtrado
    */
  def testFiltrar() {
    val conjunto1 = Conjunto((x: Int) => x > 3)
    val conjunto2 = Conjunto((x: Int) => x < 10)

    // Deja en conjunto1 los elementos de conjunto2
    val conjuntoFiltrado = Conjunto.filtrar(conjunto1, conjunto2.funcionCaracteristica)

    // 6 debe pertenecer y 11 no
    assert(conjuntoFiltrado(6))
    assert(!conjuntoFiltrado(11))
  }

  /**
    * Test de paraTodo
    */
  def testParaTodo() {
    val conjunto = Conjunto((x: Int) => x < 10)

    // No todos los elementos del conjunto son > 0
    assert(!Conjunto.paraTodo(conjunto, x => x > 0))

    // Si que todos son menores de 15
    assert(Conjunto.paraTodo(conjunto, x => x < 15))
  }

  /**
    * Test para existe
    */
  def testExiste() {
    val conjunto = Conjunto((x: Int) => x < 10)

    // No existe en el conjunto ningun elemento mayor de 10
    assert(!Conjunto.existe(conjunto, x => x > 10))

    // Si existe en el conjunto algun elemento menor de 15
    assert(Conjunto.existe(conjunto, x => x < 15))
  }

  /**
    * Test de map
    */
  def testMap() {
    // Definicion del conjunto
    val conjunto = Conjunto((x: Int) => x < 10)

    // Mapeo: sumar 25 a todos los elementos del conjunto
    val resultado = Conjunto.map(conjunto, (x => x + 25))

    // 30 y31 pertenecen al conjunto resultado, ya que
    // 5 y 6 pertenecen al conjunto de partida
    assert(resultado(30))
    assert(resultado(31))

    //Otros ejemplos que tambien estan en el conjunto
    assert(resultado(34))
    assert(resultado(25))
    assert(resultado(24))

    // 125 no pertenece, porque 100 no esta en el conjunto
    // de partida
    assert(!resultado(125))
    //Los mismo pasa con 35 seria 10 y no esta en el conjunto
    assert(!resultado(35))


  }

}
