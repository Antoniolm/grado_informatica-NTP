package Racional

/**
  * Created by LENOVO on 02/05/2017.
  */

class Racional(n:Int,d:Int) {
  //Creación de guarda o condicion(precondiciones) para crear objetos
  require(d!=0)

  private val mcd=maximoComunDivisor(n,d)

  val numerador:Int=n/mcd
  val denominador:Int=d/mcd

  //constructor auxiliar
  def this(n:Int) = this(n,1)

  override def toString=numerador +"/"+ denominador

  def menorQue(otro:Racional) ={
    numerador*otro.denominador<denominador*otro.numerador
  }

  //Calcular maximo comun divisor
  private def maximoComunDivisor(a:Int,b:Int):Int =
    if(b==0) a else maximoComunDivisor(b,a%b)
}

object EjemploUso extends App{
  val obj1=new Racional(2,3)
  val obj2=new Racional(5)
  val obj3=new Racional(8/16)
  println(obj3)
}