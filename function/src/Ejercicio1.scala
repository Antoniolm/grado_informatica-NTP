/**
  * Created by LENOVO on 03/04/2017.
  */
object Ejercicio1 extends App{

  /////////////////////////////////////////////////////////
  //
  // CTRL+MAYUS+T
  //
  ////////////////////////////////////////////////////////

  //////////////////////////////////////////////////////////////////
  //sumatorio desde a,b x
  def sumatorio(a:Int,b:Int) ={
    def auxiliar(a:Int,b:Int, acum:BigInt): BigInt = {
      if(a>b) acum
      else auxiliar(a+1,b,a+acum)
    }
    auxiliar(a,b,0)
  }

  val result=sumatorio(1,10)
  println(result);

  //Caso profesor
  def sumatorioEnteros(a:Int,b:Int):Int ={
    if(a >b) 0
    else a+sumatorioEnteros(a+1,b)
  }

  //////////////////////////////////////////////////////////////////
  def sumatorioEnterosCua(a:Int,b:Int):Int ={
    if(a>b) 0
    else (a*a)+sumatorioEnterosCua(a+1,b)
  }

  val result2=sumatorioEnterosCua(1,3)
  println(result2);

 //////////////////////////////////////////////////////////////////

  def potencia2(a:Int): Int ={
    if(a==0) 1
    else 2*potencia2(a-1)
  }

  def sumatoriaBase2(a:Int,b:Int):Int ={
    if(a>b) 0
    else potencia2(a)+sumatoriaBase2(a+1,b)
  }

  val result3=sumatoriaBase2(1,4)
  println(result3);

  //////////////////////////////////////////////////////////////////
  //Funcion generica sumatorio
  def sumatoriaGenerica(a:Int,b:Int,f:Int=>Int):Int ={
    if(a>b) 0
    else f(a)+sumatoriaGenerica(a+1,b,f)
}
val result4=sumatoriaGenerica(1,4,potencia2)
println(result3);

def sumatorioEnteros2(a:Int,b:Int) = sumatoriaGenerica(a,b,x=>x)
def sumatorioCuadrados2(a:Int,b:Int) = sumatoriaGenerica(a,b,x=>x*x)
def sumatorioPotencia2(a:Int,b:Int) = sumatoriaGenerica(a,b,potencia2)

  def sumatorioCurrificado(f:Int=>Int)(a:Int,b:Int):Int ={
    if(a>b) 0
    else f(a)+sumatorioCurrificado(f)(a+1,b)
  }
  val result5=sumatorioCurrificado(x=>x)(1,4)
  println(result5);

  val sumatorioEnteros3 = sumatorioCurrificado(x=>x)_
  sumatorioEnteros3(1,10)

  def sumatorioTailRec2(f:Int=>Int)(a:Int,b:Int):Int ={
    @annotation.tailrec
    def iterar(a:Int,acum:Int): Int = {
      if(a>b) acum
      else iterar(a+1,acum+f(a))
    }
    iterar(a,0)
  }
  val result6=sumatorioTailRec2(x=>x)(1,4)
  println("Prueba"+result6);
}
