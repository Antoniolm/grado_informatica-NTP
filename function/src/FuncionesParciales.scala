/**
  * Created by LENOVO on 03/04/2017.
  */
object FuncionesParciales extends App{
  //
  val errorManager : Int =>String = {
    case 200 => "Correct functionality"
    case 400 => "Error type 1"
    case 500 => "Error type 2"
  }

  val result=errorManager(500)

  def operacionSeguraString(cadena : String , operacion : String => String ) = {
    if(cadena== null) cadena
    else operacion(cadena)
  }

  val resultado=operacionSeguraString("Hola Pepe", {
    s => {
      val hora=System.currentTimeMillis()
      val cadenaFinal=s+"("+hora+")"
      cadenaFinal.toUpperCase
    }
  })
}
