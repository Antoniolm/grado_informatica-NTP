//Literales
4

'a'

"Hola,Mundo"

(x:Int, y:Int) => x+y

//Valores: val
val y=8

//Variables
var x=23.7

//Todo tiene un tipo asociado
//es decir son objetos
3.getClass.getName
8.3.getClass.getName
'c'.getClass.getName
"Hola, Pepe".getClass.getName
true.getClass.getCanonicalName

val f=(x:Int,y:Int) => x+y
f(2,3)
f.getClass.getName

//Concepto de inmutabilidad
val array:Array[Int] = new Array(10)
array.length
array(1)=3

//Creación de clases
class NumeroComplejo(val x:Double, val y:Double)
val nc1=new NumeroComplejo(2.3,4.7)

println("x :" +nc1.x+" y: "+nc1.y)
