
trait UtilidadesHtml{
  def quitarMarca(entrada:String):String ={
    entrada.replaceAll("""</?\w[^>]*>""","").
      replaceAll("<.*>","")
  }
}

trait OperacionSeguraString{
  def eliminarEspacios(cadena:String):Option[String] = {
    Option (cadena) map (_.trim) filterNot(_.isEmpty)
    }
}

class Pagina(val contenido:String) extends UtilidadesHtml with OperacionSeguraString{
  def comoTextoPlano=quitarMarca(contenido)

}
class ejemploTrait {
  val pagina=new Pagina("<html><body><h1>Introducción</h1></body></html>")
  println(pagina.comoTextoPlano)
  println(pagina.eliminarEspacios("asdfad  asdf ad     adsfa"))
}
