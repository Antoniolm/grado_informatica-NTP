class Multiplicador(val x:Int){
  def producto(y:Int) = x*y
}

//Relacion clase - objeto companion para facilitar
// el mecanismo de creación de objetos: factoria
object Multiplicador{
  def apply(x:Int)=new Multiplicador(x)
}

object ejemploObjeto3 extends App{
  val porTres=new Multiplicador(3)
  val res1=porTres.producto(5)

  //Simplificar la construcción
  val porCinco=Multiplicador(5)

}
