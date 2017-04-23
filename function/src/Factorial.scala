/**
  * Created by LENOVO on 28/03/2017.
  */
object Factorial extends App{

    //@annotation.tailrec
    def factorial(x:Int):Int = {
      if(x==0) 1
      else x*factorial(x-1)
    }

    val fact15=factorial(15)
    println(fact15)

    //Para realizar este metodo de forma recursiva seria
    @annotation.tailrec
    def factTailRecursion(x:Int,acum:Int):Int = {
      if(x ==0 || x==1) acum
      else factTailRecursion(x-1,x*acum)
    }

    val fact15TR=factTailRecursion(acum =1, x=15)
    println(fact15TR)

    //Para que sea mas natural para el usuario deberiamos
    def factTR2(x:BigInt) = {
      def auxiliar(x:BigInt, acum:BigInt): BigInt = {
        if(x==0 || x==1) acum
        else auxiliar(x-1,x*acum)
      }

      auxiliar(x,1)
    }

    val fact30=factTR2(30)
    println(fact30)
}



