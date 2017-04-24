//Clase A
class A{
  def mensaje="Saludos desde A"

  override def toString=getClass.getName
}

class B extends A

class C extends B{
  override def mensaje="Saludo desde C ->"+super.mensaje
}

val objA=new A
val objB=new B
val objC=new C

objA.mensaje
objB.mensaje
objC.mensaje

val lista=List(new A,new B,new C)

val refA:A=new A
val refB:A=new B
val refC:A=new C
