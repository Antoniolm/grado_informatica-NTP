//match es muy potente
val x=10
val y=3

val maximo= x>y match {
  case true => x
  case false => y
}

var error=500
val mensaje: Any = error match{
  case 200 => "ok"
  case 400 => {
    println("Error de ejecucion")
    "Error 400:......"
  }
  case 500 => {
    println("Error sintactico")
    "Error 500:...."
  }
}

val dia="lunes"

val laborable=dia match{
  case "lunes" | "martes" | "miercoles" | "jueves" | "viernes" => "laborable"
  case "sabado" | "domingo" => "festivo"
}

//Caso default
val mensaje1="cualquier cosa"

val estado = mensaje1 match{
  case "OK" =>200
  case otro => println(s"Recibido: $otro")
    -1
}
