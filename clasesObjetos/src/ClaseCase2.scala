
abstract class Notificacion

case class Email(direccion:String,tituo:String,cuerpo:String) extends Notificacion
case class SMS(numeroOrigen:String,mensaje:String) extends Notificacion

case class MensajeVoz(contacto:String,enlace:String) extends Notificacion

object ClaseCase2 extends App{
  def mostrarNotificaciones(notificacion:Notificacion):String={
    notificacion match {
      case Email(direccion,titulo, _) =>
        "Recibido correo de"+ direccion+" con titulo: "+titulo
      case SMS(numero,mensaje) =>
        "Recibido SMS de"+ numero+" con mensaje: "+mensaje
      case MensajeVoz(contacto,enlace) =>
        "Recibido mensaje de voz de"+ contacto +" con enlace: "+enlace

    }
  }

  val mensajeSMS=SMS("12345","Estas ahi?")
  val mensajeVoz=MensajeVoz("Tomas","http://mensajevoz/id/mensajes")

  println(mostrarNotificaciones(mensajeSMS))
  println(mostrarNotificaciones(mensajeVoz))

}
