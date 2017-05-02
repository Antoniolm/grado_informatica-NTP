/**
  * Created by LENOVO on 25/04/2017.
  */
class A(final val datoFinal:Int, val datoNormal:Int){
  final override def toString():String = {
    s"datoFinal: $datoFinal - datoNormal: $datoNormal"
  }
}

class B( dato1:Int, dato2:Int) extends A(dato1,dato2){
  override def toString: String = "asdasdasd"
}

class ejemploDeFinal {



}
