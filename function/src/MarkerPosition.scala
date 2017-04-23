/**
  * Created by LENOVO on 03/04/2017.
  */
object MarkerPosition {
  val multiplicarPor2 = (x:Int) => x*2

  //Uso de marcador de posicion
  //Para usarlo debe
  //-El argumento solo aparece una vez a la derecha
  val multiplcadorPor2B:Int =>Int = _*2

  def combinacion(x:Int,y:Int, f:(Int,Int)=>Int) = f(x,y)

  val resultado= combinacion(2,3, (x,y) => x*y)

  val resultado2= combinacion(2,3 ,_*_)

  def operacionSeguraString(cadena : String , operacion : String => String ) = {
    if(cadena== null) cadena
    else operacion(cadena)
  }

  def invertir(cadena : String)=cadena.reverse

  operacionSeguraString("Hola",_.reverse)

}
