//Estructura while(sentencia)
//Debemos de no usarla porque no es funcional

var i: Int = 10

while(i>0){
  println("Valor de i:"+i)
  i=i-1
}


//Do while

i=10

do{
  println("Valor de i en do-while:"+i)
  i=i-1
}while(i>=0)

//Expresion for for- comprehension
//--Generadores--
val x=1 to 10
val y=1 until 10
val z=1 to 10 by 2
val w=10 to 1 by -2
/////////////////

for(i <- 1 to 10) {
  println("Valor e i:" + i)
}

//Yield nos sirve para crear colleciones
for(i <- 1 to 10) yield i

//Tambien podriamos hacer esto
//Aprovecha la creación de la colección para
//hacer mas cosas como el println y solo
//metera en la colección lo que indique al final
for(i <- 1 to 2) yield{
  println("Valor de i:"+i)
  i
}

//For doble
for{i <- 1 to 5
    j <- 2 to 4} yield {
    println("("+i+","+j+")")
    i+j
}

//Parametrización
val saludos=new Array[String](3)

saludos(0)="Hola"
saludos(1)="mundo"
saludos(2)="cruel"

for(i <- 0 until saludos.length){
  println(saludos(i))
}