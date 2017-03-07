
/**
 * Clase Empleado
 */
public class Empleado {
   // Dato miembro para almacenar el nombre
   private int dni;

   // Dato miembro para almacenar el nombre
   private String nombre;

   // Dato miembro para almacenar primer apellido
   private String apellido;

   // Dato miembro para almacenar el sueldo
   private String correo;

   // Dato miembro para almacenar el departamento
   private String department;

   // Dato miembro para almacenar el departamento
   private String division;

   // Constructor
   public Empleado(int aDni,String nombre, String primerApellido,
           String aMail, String dapartamento) {
      this.dni=aDni;
      this.nombre = nombre;
      this.apellido = primerApellido;
      this.correo = aMail;
   }

   // Metodo para asignar el nombre
   public void asignarNombre(String nombre) {
      this.nombre = nombre;
   }

   // Metodo para acceder al nombre
   public String obtenerNombre() {
      return nombre;
   }

   // Metodo para asignar el apellido
   public void asignarPrimerApellido(String primerApellido) {
      this.apellido = primerApellido;
   }

   // Metodo para obtener el primer apellido
   public String obtenerPrimerApellido() {
      return apellido;
   }

   // Metodo para asignar el valor de sueldo
   public void asignarMail(String aMail) {
      this.correo = aMail;
   }

   // Metodo para obtener el sueldo
   public String obtenerMail() {
      return correo;
   }

   // Metodo par asignar el departamento
   public void asignarDepartament(String departamento) {
      this.department = departamento;
   }

   // Metodo para obteber el valor del dato miembro departamento
   public String obtenerDivision() {
      return this.division;
   }

   // Metodo par asignar el departamento
   public void asignarDivision(String aDivision) {
      this.division = aDivision;
   }

   // Metodo para obteber el valor del dato miembro departamento
   public String obtenerDepartament() {
      return department;
   }

   // Recupera nombre y primer apellido
   public String obtenerNombreApellido() {
      return String.format("%s %s", obtenerNombre(), obtenerPrimerApellido());
   }


   // Metodo toString
   @Override
   public String toString() {
      return String.format("%-8s %-8s %8.2f   %s",
              obtenerNombre(), obtenerPrimerApellido(), obtenerMail(),
              obtenerDepartament());
   }
}
