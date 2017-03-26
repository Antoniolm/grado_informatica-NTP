
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


import java.io.IOException;
import java.util.Map;

/**
 * Práctica 1 NTP
 */
public class ListadoTest {
    private static Listado listado;

    /**
     * Codigo a ejecutar antes de realizar las llamadas a los métodos
     * de la clase; incluso antes de la propia instanciación de la
     * clase. Por eso el método debe ser estatico
     */
    @BeforeClass
    public static void inicializacion() {
        System.out.println("Metodo inicializacion conjunto pruebas");
        // Se genera el listado de empleados
        try {
            listado = new Listado("./data/datos.txt");
        }catch(IOException e){
            System.out.println("Error en lectura de archivo de datos");
        };

        // Una vez disponibles los empleados se leen las listas
        // de asignaciones de empleados a cada grupo de las diferentes
        // asignaturas consideradas
        try {
            listado.loadDivision("./data/asignacionDIVNA.txt");
            listado.loadDivision("./data/asignacionDIVID.txt");
            listado.loadDivision("./data/asignacionDIVSW.txt");
            listado.loadDivision("./data/asignacionDIVHW.txt");
            listado.loadDivision("./data/asignacionDIVSER.txt");
            listado.loadDepartment("./data/asignacionDEPNA.txt");
            listado.loadDepartment("./data/asignacionDEPSB.txt");
            listado.loadDepartment("./data/asignacionDEPSM.txt");
            listado.loadDepartment("./data/asignacionDEPSA.txt");
        } catch (IOException e) {
            System.out.println("Error en lectura de archivos de asignacion");
        }
        System.out.println();
    }

    /**
     * Test para comprobar que se ha leido de forma correcta la
     * informacion de los empleados (al menos que el listado contiene
     * datos de 100 empleados)
     * @throws Exception
     */
    @Test
    public void testConstruccionListado() throws Exception{
        assert(listado.obtainNumberEmploy() == 1000);
    }

    /**
     * Test del procedimiento de asignacion de grupos procesando
     * los archivos de asignacion. Tambien implica la prueba de
     * busqueda de empleados sin grupo asignado en alguna asignatura
     * @throws Exception
     */
    @Test
    public void testCargarArchivosAsignacion() throws Exception {
        // Se obtienen los empleados no asignados a cada asignatura
        // y se comprueba su valor
        assert(listado.buscarEmpleadosSinDepartamento(Division.DIVNA).size() == 49);
        assert(listado.buscarEmpleadosSinDepartamento(Division.DIVID).size() == 54);
        assert(listado.buscarEmpleadosSinDepartamento(Division.DIVSW).size() == 42);
        assert(listado.buscarEmpleadosSinDepartamento(Division.DIVHW).size() == 44);
        assert(listado.buscarEmpleadosSinDepartamento(Division.DIVSER).size() == 49);

    }

    /**
     * Prueba para el procedimiento de conteo de grupos para cada una
     * de las asignaturas
     */
    @Test
    public void testObtenerContadoresDepartamentos(){
        // Se obtienen los contadores para la asignatura ES
        Map<Departamento, Long> contadores = listado.obtenerContadoresDepartamento(
                Division.DIVSER);
        contadores.keySet().stream().forEach(key -> System.out.println(
                key.toString() + "- " + contadores.get(key)));
        // Se comprueba que los valores son DEPNA = 49, DEPSB = 48, DEPSM = 53, DEPSA = 41
        Long contadoresReferencia[]={49L,48L,53L,41L};
        Long contadoresCalculados[]=new Long[4];
        assertArrayEquals(contadores.values().toArray(contadoresCalculados),
                          contadoresReferencia);
    }

    /**
     * Prueba del procedimiento general de obtencion de contadores
     * para todas las asignaturas
     * @throws Exception
     */
    @Test
    public void testObtenerContadoresDivisionDepartamento() throws Exception {
        // Se obtienen los contadores para todos los grupos
        Map<Division, Map<Departamento, Long>> contadores =
                listado.obtenerContadoresDivisionDepartamento();

        // Se comprueban los valores obtenenidos con los valores por referencia
        Long contadoresReferenciaNA[]={49L,53L,53L,58L};
        Long contadoresReferenciaID[]={54L,49L,42L,43L};
        Long contadoresReferenciaHW[]={44L,43L,67L,62L};
        Long contadoresReferenciaSW[]={42L,52L,45L,53L};
        Long contadoresReferenciaSER[]={49L,48L,53L,41L};

        // Se comprueban los resultado del metodo con los de referencia
        Long contadoresCalculados[]=new Long[4];
        assertArrayEquals(contadores.get(Division.DIVNA).values().
                toArray(contadoresCalculados),contadoresReferenciaNA);
        assertArrayEquals(contadores.get(Division.DIVID).values().
                toArray(contadoresCalculados),contadoresReferenciaID);
        assertArrayEquals(contadores.get(Division.DIVHW).values().
                toArray(contadoresCalculados),contadoresReferenciaHW);
        assertArrayEquals(contadores.get(Division.DIVSW).values().
                toArray(contadoresCalculados),contadoresReferenciaSW);
        assertArrayEquals(contadores.get(Division.DIVSER).values().
                toArray(contadoresCalculados),contadoresReferenciaSER);
    }

    // Aqui habria que completar los casos de prueba para el resto de
    // metodos a ofrecer por la clase Listado
    /**
     * Test del procedimiento de asignacion de grupos procesando
     * los archivos de asignacion. Tambien implica la prueba de
     * busqueda de empleados sin grupo asignado en alguna asignatura
     * @throws Exception
     */
    @Test
    public void testEmployWithoutDivision() throws Exception {
        assert(listado.buscarEmpleadosSinDivision().size() == 213);
    }

    /**
     * Prueba para detectar empleados con division y sin departamento
     * @throws Exception
     */
    @Test
    public void testDivDep() throws Exception {
        assert(listado.buscarEmpleadosConDivisionSinDepartamento().size() == 189);

    }

    /**
     * Prueba para detectar empleados con dni repetido
     * @throws Exception
     */
    @Test
    public void testDniRepeated() throws Exception {
        assert(listado.hayDnisRepetidos() == false);
    }

    /**
     * Prueba para obtener los empleados con dni repetido
     * @throws Exception
     */
    @Test
    public void testObtainDniRepeated() throws Exception {
        assert(listado.obtenerDnisRepetidos().size() == 0);
    }

    /**
     * Prueba para detectar los empleados con mail repetido
     * @throws Exception
     */
    @Test
    public void testMailRepeated() throws Exception {
        assert(listado.hayCorreosRepetidos() == true);
    }

    /**
     * Prueba para obtener los empleados con mail repetido
     * @throws Exception
     */
    @Test
    public void testObtainMailRepeated() throws Exception {
        assert(listado.obtenerCorreosRepetidos().size() == 8);
    }

    /**
     * Prueba para asignar de forma equilibrada a los empleados
     * @throws Exception
     */
    @Test
    public void testEqualityDivision() throws Exception {
        listado.asignarEmpleadosSinDivision();
        System.out.println("-------Asignación Equilibrada de departamento y division-------");
        System.out.println("Sin division ->"+listado.buscarEmpleadosSinDivision().size());
        System.out.println(" DIVHW employ->"+listado.totalEmpleadosDivision(Division.DIVHW));
        System.out.println(" DIVID employ->"+listado.totalEmpleadosDivision(Division.DIVID));
        System.out.println(" DIVSER employ->"+listado.totalEmpleadosDivision(Division.DIVSER));
        System.out.println(" DIVSW employ->"+listado.totalEmpleadosDivision(Division.DIVSW));

        listado.asignarEmpleadosDepartamento(Division.DIVSER);
        listado.asignarEmpleadosDepartamento(Division.DIVHW);
        listado.asignarEmpleadosDepartamento(Division.DIVSW);
        listado.asignarEmpleadosDepartamento(Division.DIVID);
        System.out.println("Sin departamento ->"+listado.buscarEmpleadosConDivisionSinDepartamento().size());
        System.out.println("-------DIVSER-------");
        System.out.println("DEPSB ->"+listado.totalEmpleadoDeptDivision(Division.DIVSER,Departamento.DEPSB));
        System.out.println("DEPSM ->"+listado.totalEmpleadoDeptDivision(Division.DIVSER,Departamento.DEPSM));
        System.out.println("DEPSA ->"+listado.totalEmpleadoDeptDivision(Division.DIVSER,Departamento.DEPSA));
        System.out.println("-------DIVHW-------");
        System.out.println("DEPSB ->"+listado.totalEmpleadoDeptDivision(Division.DIVHW,Departamento.DEPSB));
        System.out.println("DEPSM ->"+listado.totalEmpleadoDeptDivision(Division.DIVHW,Departamento.DEPSM));
        System.out.println("DEPSA ->"+listado.totalEmpleadoDeptDivision(Division.DIVHW,Departamento.DEPSA));
        System.out.println("-------DIVID-------");
        System.out.println("DEPSB ->"+listado.totalEmpleadoDeptDivision(Division.DIVID,Departamento.DEPSB));
        System.out.println("DEPSM ->"+listado.totalEmpleadoDeptDivision(Division.DIVID,Departamento.DEPSM));
        System.out.println("DEPSA ->"+listado.totalEmpleadoDeptDivision(Division.DIVID,Departamento.DEPSA));
        System.out.println("-------DIVSW-------");
        System.out.println("DEPSB ->"+listado.totalEmpleadoDeptDivision(Division.DIVSW,Departamento.DEPSB));
        System.out.println("DEPSM ->"+listado.totalEmpleadoDeptDivision(Division.DIVSW,Departamento.DEPSM));
        System.out.println("DEPSA ->"+listado.totalEmpleadoDeptDivision(Division.DIVSW,Departamento.DEPSA));
    }
}