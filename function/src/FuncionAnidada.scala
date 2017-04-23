/**
  * Created by LENOVO on 28/03/2017.
  */
object FuncionAnidada {

  /**
    *  Funcion para calcular...
    * @param x
    * @param y
    * @param z
    * @return
    */
  def max(x:Int, y:Int, z:Int) = {
    def max(x:Int, y:Int) = if(x>y) x else y

    max(x,max(y,z))

  }
}
