
//Clase con datos y metodos
class Usuario{
  val nombre:String="Administrador"
  def prompt:String=s"$nombre>"
  override def toString=s"Usuario>$nombre"
  println("En clase Usuario")
}

val usuario=new Usuario
println(usuario)
println(usuario.prompt)