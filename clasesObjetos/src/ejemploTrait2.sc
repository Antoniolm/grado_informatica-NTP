class A1{
  def mostrar="Clase A1"
}

trait B1{self:A1 =>
  override def toString="B: "+mostrar
}

//class C extends B1

class C extends A1 with B1
val obj =new C
val a=new A1 with B1

