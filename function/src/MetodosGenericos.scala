/**
  * Created by LENOVO on 28/03/2017.
  */
object MetodosGenericos {
  /**
    *
    * @param lista
    * @tparam A
    * @return
    */
  def deleteFirst[A](lista: List[A]) = lista.tail

  var result1=deleteFirst(List(1,2,3))
  var result2=deleteFirst(List(3.5,8.3,2.0))

  var result3 =deleteFirst(List("Hola","Adios"))
  
}
