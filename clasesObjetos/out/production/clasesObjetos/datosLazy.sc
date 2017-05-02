class PuntoAleatorio{
  val x={
    println("Asignatura de x: ")
    util.Random.nextInt
  }
  lazy val y={
    println("Asignación de y: ")
    util.Random.nextInt
  }
}

val p1=new PuntoAleatorio
println(s"Ubicación en ${p1.x}, ${p1.y}")

