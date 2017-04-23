def duplicar(x:Int) = x*2

//Asignación
val funcion :(Int) => Int = duplicar

funcion(3)

val otra= funcion

otra(5)

val funcion2= duplicar _

def calculateMax(a:Int ,b:Int) = if(a>b) a else b

val f2= calculateMax _
f2(23,67)

