//Creación de listas
val lista1=List(1,2,3,4)
val lista2=List(5,6)

//Concatenacion operador :::
//operan en realidad con el objeto que esta a
//la derecha
val lista12= lista1:::lista2

//Agregar un elemento al principio

val lista3 = 1::lista1

//Programación funcional incorporada
// en la clase (no flujo previo)
val mayor2 = lista1.filter(x => x>2)

val cuantosMayor2 = lista1.count(x => x >2)

//conjunto de operacoines muy amplio
lista1.drop(2)
lista1.dropRight(2)
println(lista1)

val resultado=lista1.exists(x => x%2 ==0)

lista1.length

//Obtener el primer elemento de la lista
lista1.head
lista1.tail

lista1.foreach(x => println(x))