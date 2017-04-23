/**
  * Created by LENOVO on 27/03/2017.
  */
object OperadoresRelacionales extends App{
  //Operadores relacionales: version doble (&&)
  //version simple (&). Formato doble: ejecución lazy
  // (se detiene la comprobación en cuando se puede evaluar)
  // version simple evalua todas las comprobaciónes aunque
  //ya se sepa que no se cumplen las condiciones
  val a=9
  val b=7

  lazy val condicion ={
    if(a>b){
      println("Comprobacion de condicion1: a>b")
      true
    }
    else{
      println("Comprobación de condicion1: a<=b")
      false
    }
  }

  lazy val condicion2={
    if((a+b)%2 ==0){
      println("Comprobación de condicion2: par")
      true
    }
    else{
      println("Comprobación de condicion2; impar")
      false
    }
  }

  val resultado1:Boolean = condicion || condicion2
  val resultado2:Boolean = {condicion | condicion2}
}
