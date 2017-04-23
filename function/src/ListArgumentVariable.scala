/**
  * Created by LENOVO on 28/03/2017.
  */
object ListArgumentVariable extends App{

  def sumar(numero: Int *):Int = {
    var total=0
    for(i <- numero) total+=i
    total
  }

  var resultado=sumar(1)
  resultado=sumar(2,3)
  resultado=sumar(4,6,7,8)

  //function with more list of argument
  def max(x:Int)(y:Int)= if(x > y) x else y

  //val f=max(3)

}
