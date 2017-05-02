object UtilidadesHtml{
  def quitarMarca(entrada:String):String ={
    entrada.replaceAll("""</?\w[^>]*>""","").
            replaceAll("<.*>","")
  }
}

object ejemploObjeto2 extends App{

}
