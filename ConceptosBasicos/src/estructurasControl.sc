//Definicion de rangos
val x= 1 to 6
val y= 1 until 10

//indicación del paso
val z=1 to 7 by 2

//Posibilidad de definir pasos
//negativos
val w= 10 to 1 by -1

//For comprehension ( es una
// expresión)
for( x <- 1 to 10) {
  println(x)
}

//Uso de yield
val resultado=for(x <- 1 to 7) yield {
  println(x)
  x
}

println(resultado)

//Es posible definir condiciones
val mult3 = for(i <- 1 to 1000
                if i%3==0
                if i%7==0) yield i

