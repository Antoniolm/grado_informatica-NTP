//Conveniencia: nomenclatura Java
//camelCase: componerValores
//mayusculas solo para clases

val b:Int=3
val `b.a`:Int=10
val $3=23

val var1:Byte=3

Byte.MaxValue
Byte.MinValue

Short.MaxValue
Short.MinValue

Int.MaxValue
Int.MinValue

Long.MaxValue
Long.MinValue

Float.MaxValue
Float.MinValue
Float.MinPositiveValue

Double.MaxValue
Double.MinValue
Double.MinPositiveValue

//permitidas conversiones de ensanchamientoval
//No permite el estrechamiento
var var2:Short=3
var var3:Double=var2

var2=var3.toByte

val var5=0xff195
val var6=10l