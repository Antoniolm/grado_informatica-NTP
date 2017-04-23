
//Definicion de funciones
def max(x: Int, y: Int) : Int = {
  if(x>y)x
  else y
}

//Podemos hacerlo mas simplificada
def max2(x: Int, y: Int) = if(x>y) x else y

max2(8,10)

//Otro tipo de funcion
def mostrarSaludo = "Hola, Mundo"

//No podemos usarlo con ()
mostrarSaludo


