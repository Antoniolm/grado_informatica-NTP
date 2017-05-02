/**
  * Created by LENOVO on 25/04/2017.
  */
package pruebaAccesibilidad1

private [pruebaAccesibilidad1] class Configuracion{
  val url="http://localhost"
}

class Autenticacion {
  private [this] val password="1234"

  def validar= password.size > 0

  def comparar(otro:Autenticacion):Boolean ={
    password== otro.password
  }
}
