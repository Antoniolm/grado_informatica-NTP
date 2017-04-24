
class Usuario(val nombre:String){
  def prompt=s"$nombre"
  override def toString=s"Usuario>$nombre"
}

val usuario = new Usuario("pepe")

val usuarios=List(new Usuario("pepe"),
                 new Usuario("luis"),
                 new Usuario("Andres"))

val longitudes = usuarios map(_.nombre.size)
val longitudes1 = usuarios map (objeto => objeto.nombre.size)

val conD = usuarios.filter(_.nombre contains "d")
val promptConD =  conD map (_.prompt)

