//Funcion mas sencilla
def saludo="hola"

saludo

def saludo1:String = "hola"

def multiply(x:Int, y:Int):Int = x*y

val multiplyResult=multiply(2,3)

def quitWhiteInit(s:String):String ={
  if(s==null) return null
  s.trim()
}

val result1=quitWhiteInit(null)
val result2=quitWhiteInit("    Pepe")

//Procedimiento: funcion que no
//devuelve nada
def show(d:Double):Unit = {
  println(f"Valor = $d%.2f")
}

show(3.141593)

//Usar bloques en la llamada
show{2.785*0.15+24}




