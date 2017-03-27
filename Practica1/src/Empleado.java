
/**
 * Clase Empleado
 */
public class Empleado {
   // Dato miembro para almacenar el nombre
   private String dni;

   // Dato miembro para almacenar el nombre
   private String nombre;

   // Dato miembro para almacenar primer apellido
   private String apellido;

   // Dato miembro para almacenar el sueldo
   private String correo;

   // Dato miembro para almacenar el departamento
   private Departamento department;

   // Dato miembro para almacenar el departamento
   private Division division;

   // Constructor
   public Empleado(String aDni,String nombre, String primerApellido,
           String aMail) {
      this.dni=aDni;
      this.nombre = nombre;
      this.apellido = primerApellido;
      this.correo = aMail;
      this.department=Departamento.DEPNA;
      this.division=Division.DIVNA;
   }

   // Metodo para asignar el nombre
   public void assignDni(String aDni) {
      this.nombre = aDni;
   }

   // Metodo para acceder al nombre
   public String obtainDni() {
      return this.dni;
   }

   // Metodo para asignar el nombre
   public void assignName(String nombre) {
      this.nombre = nombre;
   }

   // Metodo para acceder al nombre
   public String obtainName() {return nombre; }

   // Metodo para asignar el apellido
   public void assignLastName(String primerApellido) {
      this.apellido = primerApellido;
   }

   // Metodo para obtener el primer apellido
   public String obtainLastName() {
      return apellido;
   }

   // Metodo para asignar el valor de sueldo
   public void assignnMail(String aMail) {
      this.correo = aMail;
   }

   // Metodo para obtener el sueldo
   public String obtainMail() {
      return correo;
   }

   // Metodo par asignar el departamento
   public void assignDepartment(Departamento departamento) {
      this.department = departamento;
   }

   // Metodo para obteber el valor del dato miembro departamento
   public Departamento obtainDepartment() {
      return department;
   }

   // Metodo par asignar la division
   public void assignDivision(Division aDivision) {
      this.division = aDivision;
   }

   // Metodo para obteber el valor del dato miembro departamento
   public Division obtainDivision() {
      return this.division;
   }

   // Metodo toString
   @Override
   public String toString() {
      return String.format("%s %s %s %s %s %s",
              obtainDni(), obtainName(), obtainLastName(), obtainMail(), obtainDepartment(), obtainDivision() );
   }
}
