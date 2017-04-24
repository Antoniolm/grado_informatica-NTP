
class Coche(val marca:String, var enUso:Boolean){
  def reservar(r:Boolean):Unit ={
    enUso=r
  }

  override def toString=s"$marca - $enUso"
}

val c1=new Coche("Toyota",true)
c1.reservar(false)
println(c1)

class Renault(val color:String,enUso:Boolean) extends Coche("Renault",enUso){
  override def toString=s"$marca - $enUso - $color"
}

val c2=new Renault("rojo",false)
println(c2)