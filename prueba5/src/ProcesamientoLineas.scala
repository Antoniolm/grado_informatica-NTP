/**
  * Created by LENOVO on 21/03/2017.
  */
import scala.io.Source

object ProcesamientoLineas {

  def printLines(fileName : String) : Unit = {
    val lines: Seq[String] =Source.fromFile(fileName).getLines().toList

    for(line <- lines){
      println(line.length+" | "+line)
    }
  }

  def calculateAnchoTamLinea(line : String) = line.length.toString.length

  def imprimirlineasV2(fileName : String) : Unit ={
    val lines= Source.fromFile(fileName).getLines().toList

    var maxWidthTam=0
    for(line <- lines){
      maxWidthTam=  maxWidthTam.max(calculateAnchoTamLinea(line))
    }

    //maximo ancho contiene el maximo espacio que necesito para escribir
    //el maximo tamañ o de cada linea
    for(line <- lines){
      val tamLinea = calculateAnchoTamLinea(line)
      val fill = " " * (maxWidthTam - tamLinea)
      println(fill+" "+ line.length+" | "+line)
    }
  }

  def imprimirlineas3(fileName : String) : Unit ={
    val lines= Source.fromFile(fileName).getLines().toList

    val lineaMasLarga= lines.reduceLeft((a,b) => if(a.length > b.length) a else b)
    var maxWidthTam=  calculateAnchoTamLinea(lineaMasLarga)


    //maximo ancho contiene el maximo espacio que necesito para escribir
    //el maximo tamañ o de cada linea
    for(line <- lines){
      val tamLinea = calculateAnchoTamLinea(line)
      val fill = " " * (maxWidthTam - tamLinea)
      println(fill+" "+ line.length+" | "+line)
    }
  }

  def main(args: Array[String]): Unit = {
    if( args.length>0){
      //printLines(args(0))
      imprimirlineasV2(args(0))
    }
    else{
      Console.err.println("Introduzca nombre de archivo")
    }


  }
}
