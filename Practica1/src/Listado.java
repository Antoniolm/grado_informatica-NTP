import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by Antonio David López Machado on 07/03/2017.
 */
public class Listado {
    Map<String, Empleado> list;
    /**
     * Constructor
     * */
    public Listado(String fileName)throws IOException{
        list =  new HashMap<String,Empleado>();
        Stream<String> lines= Files.lines(Paths.get(fileName));
        list= lines.map(line -> {
            return createEmpleado(line);
        }).
        collect(Collectors.
                toMap(employ -> employ.obtainDni(), Function.identity()));
    }

    /**
     * Metodo que asignara empleados en un departamento. Los empleados a asignar seran
     * deterinados por el fichero pasado como parametro
     * */
    public void loadDepartment(String fileName)throws IOException{
        Stream<String> lines= Files.lines(Paths.get(fileName));

        String department=lines.findFirst().get();
        List<String> departmentList = Files.lines(Paths.get(fileName)).skip(2).collect(Collectors.toList());

        list.entrySet().stream().forEach(emp -> {
            if(departmentList.contains(emp.getKey()))
                emp.getValue().assignDepartment(Departamento.valueOf(department));
        });
    }

    /**
     * Metodo que asignara empleados en una division. Los empleados a asignar seran
     * deterinados por el fichero pasado como parametro
     * */
    public void loadDivision(String fileName)throws IOException{
        Stream<String> lines= Files.lines(Paths.get(fileName));

        String division=lines.findFirst().get();
        List<String> divisionList = Files.lines(Paths.get(fileName)).skip(2).collect(Collectors.toList());

        list.entrySet().stream().forEach(emp -> {
            if(divisionList.contains(emp.getKey()))
                emp.getValue().assignDivision(Division.valueOf(division));
        });

    }

    /**
     * Devolvera el número de empleados en nuestro listado
     * */
    public int obtainNumberEmploy(){
        return list.size();
    }

    /**
     *  Metodo que contara el numero de empleados departamento en una division concreta
     * */
    public Map<Departamento, Long> obtenerContadoresDepartamento(Division aDivision) {
        return list.entrySet().stream().filter(employ -> employ.getValue().obtainDivision() == aDivision).
                map(employ -> employ.getValue().obtainDepartment()).
                sorted(Comparator.naturalOrder()).
                collect(Collectors.groupingBy(Function.identity(),
                        TreeMap::new,
                        Collectors.counting()));
    }

    /**
     *  Metodo que contara el numero de empleados por division y departamento
     * */
    public Map<Division, Map<Departamento, Long>> obtenerContadoresDivisionDepartamento(){
        Map<Division, Map<Departamento, Long>> mapa=new TreeMap<>();
        Stream.of(Division.values()).
                forEach(division -> mapa.put(division,obtenerContadoresDepartamento(division)));
        return mapa;
    }


    /**
     * Metodo para buscar los empleados sin division asignada: es decir,
     * en el dato miembro division tendran el valor DIVNA
     * */
    public List<Empleado> buscarEmpleadosSinDivision(){
         return list.entrySet().stream().
                 filter(employ -> employ.getValue().obtainDivision() == Division.DIVNA).
                 map(employ -> employ.getValue()).collect(Collectors.toList());
    }

    /**
     *  Metodo que busca empleados sin departamento en una determinada division
     * */
    public List<Empleado> buscarEmpleadosConDivisionSinDepartamento(){
        return list.entrySet().stream().
                filter(employ -> employ.getValue().obtainDepartment() == Departamento.DEPNA &&
                        employ.getValue().obtainDivision() != Division.DIVNA).
                map(employ -> employ.getValue()).collect(Collectors.toList());
    }

    /**
     *  Metodo que busca empleados sin departamento en una determinada division
     * */
    public List<Empleado> buscarEmpleadosSinDepartamento(Division division){
        return list.entrySet().stream().
                filter(employ ->  employ.getValue().obtainDivision()== division&&
                        employ.getValue().obtainDepartment() == Departamento.DEPNA).
                map(employ -> employ.getValue()).collect(Collectors.toList());
    }

    /**
     *  Devuelve si hay o no empleados con dni repetido
     * */
    public boolean hayDnisRepetidos(){
        boolean result=false;
        //Agrupación por dni de nuestros empleados
        Map<String, Long> dniList = list.entrySet().stream().collect(Collectors.groupingBy(
                employ -> employ.getValue().obtainDni(),
                Collectors.counting()));

        //Comprobamos si hay alguno repetido
        List<String> dniRepeated = dniList.entrySet().stream().filter(dni -> dni.getValue() > 1)
                .map(dni -> dni.getKey())
                .collect(Collectors.toList());

        if(dniRepeated.size()>0)
            result=true;

        return result;
    }

    /**
     *  Método que devolvera un map que contiene todos los empleados
     *  de nuestro listado que tengan el dni repetido
     * */
    public Map<String,List<Empleado>> obtenerDnisRepetidos(){
        //Agrupación por dni de nuestros empleados
        Map<String, Long> dniList = list.entrySet().stream().collect(Collectors.groupingBy(
                employ -> employ.getValue().obtainDni(),
                Collectors.counting()));

        //Comprobamos si hay alguno repetido
        List<String> dniRepeated = dniList.entrySet().stream().filter(dni -> dni.getValue() > 1)
                .map(dni -> dni.getKey())
                .collect(Collectors.toList());

        //Obtenemos una lista de empleados con dni repetidos
        List<Empleado> employDni = list.entrySet().stream().
                filter(employ -> dniRepeated.contains(employ.getValue().obtainDni())).
                map(employ -> employ.getValue()).
                collect(Collectors.toList());

        //Obtenemos los empleados repetidos por dni
        Map<String, List<Empleado>> result = employDni.stream().collect(Collectors.groupingBy(
                employ -> employ.obtainDni()
                ));

        return result;
    }

    /**
     *  Devuelve si hay o no empleados con correo repetido
     * */
    public boolean hayCorreosRepetidos(){
        boolean result=false;
        //Agrupación por mail de nuestros empleados
        Map<String, Long> mailList = list.entrySet().stream().collect(Collectors.groupingBy(
                employ -> employ.getValue().obtainMail(),
                Collectors.counting()));

        //Comprobamos si hay alguno repetido
        List<String> mailRepeated = mailList.entrySet().stream().filter(mail -> mail.getValue() > 1)
                .map(mail -> mail.getKey())
                .collect(Collectors.toList());

        if(mailRepeated.size()>0)
            result=true;

        return result;
    }


    /**
     *  Método que devolvera un map que contiene todos los empleados
     *  de nuestro listado que tengan el correo repetido
     * */
    public Map<String,List<Empleado>> obtenerCorreosRepetidos(){
        //Agrupación por mail de nuestros empleados
        Map<String, Long> mailList = list.entrySet().stream().collect(Collectors.groupingBy(
                employ -> employ.getValue().obtainMail(),
                Collectors.counting()));

        //Comprobamos si hay alguno repetido
        List<String> mailRepeated = mailList.entrySet().stream().filter(mail -> mail.getValue() > 1)
                .map(mail -> mail.getKey())
                .collect(Collectors.toList());

        //Obtenemos una lista de empleados con mail repetidos
        List<Empleado> employEmail = list.entrySet().stream().
                filter(employ -> mailRepeated.contains(employ.getValue().obtainMail())).
                map(employ -> employ.getValue()).
                collect(Collectors.toList());

        //Obtenemos los empleados repetidos por mail
        Map<String, List<Empleado>> result = employEmail.stream().collect(Collectors.groupingBy(
                employ -> employ.obtainMail()
        ));

        return result;
    }


    /**
     *  Método que devolvera una string que contiene todos los empleados
     *  de nuestro listado
     * */
    public String toString(){
        String finalResult = list.entrySet().stream().map(emp -> emp.getValue().toString()).
                reduce((result, emp) ->
                            result.toString() + emp.toString()+"\n").toString();
        return finalResult;
    }

    private Empleado createEmpleado(String lines) {
        Pattern pattern = Pattern.compile(",");
        List<String> flowEmploy = pattern.splitAsStream(lines).collect(Collectors.toList());

        return new Empleado(flowEmploy.get(0), flowEmploy.get(1), flowEmploy.get(2), flowEmploy.get(3));
    }

    /////////////////////////////////////////////////////
    //
    //  `Parte opcional A)Asignación equitativa por division
    //
    ////////////////////////////////////////////////////
    /**
     *  Asignara division de forma equitativa todos los empleados sin division
     * */
    public void asignarEmpleadosSinDivision(){
        Map<Division, Long> divisionLongMap = totalEmpleadosPorDivision();

        //Obtenemos el numero de empleados por division maximo entre las divisiones
        Long maxDiv = divisionLongMap.entrySet().stream().map(div -> div.getValue()).reduce((x, y) -> {
            if (x > y) return x;
            else return y;
        }).orElse(new Long(0));

        //Asignamos elementos hasta que todas las divisiones tengan el mismo numero de elementos
        Stream.of(Division.values()).forEach(div -> {
            if(div!= Division.DIVNA)
                asignacionEquitativaDivision(div,maxDiv);
        });

        //El resto de empleados los dividimos entre los 4 tipos de div y los asignamos
        Long numEmployWithoutDiv=(buscarEmpleadosSinDivision().size()/4)+maxDiv;
        Stream.of(Division.values()).forEach(div -> {
            if(div!= Division.DIVNA)
                asignacionEquitativaDivision(div,numEmployWithoutDiv);
        });

        int lastEmploy= buscarEmpleadosSinDivision().size();

        //Si aun queda algun empleado sin division lo añadimos en alguna de las divisiones.
        if(lastEmploy>0){
            Stream.of(Division.values()).forEach(div -> {
                if(div!= Division.DIVNA)
                    asignacionEquitativaDivision(div, totalEmpleadosDivision(div)+lastEmploy);
            });
        }
    }

    /**
     * Metodo encargado de obtener el numero total de empleados en una division
     * @param division
     * @return
     */
    public long totalEmpleadosDivision(Division division){
        Map<Departamento, Long> departamentoLongMap = obtenerContadoresDepartamento(division);
        return departamentoLongMap.entrySet().stream().map(depart -> depart.getValue()).
                reduce((x, y) -> x+ y).orElse(new Long(0));
    }

    /**
     * Metodo que obtiene un map de divisiones y para cada division se guarda el número total
     * empleados en esa division
     * @return
     */
    private Map<Division,Long> totalEmpleadosPorDivision(){
        Map<Division,Long> result=new TreeMap<Division,Long>();
        Stream.of(Division.values()).forEach(division -> result.put(division, totalEmpleadosDivision(division)));

        return result;
    }

    /**
     * Metodo que nos permite asignar division hasta que todos esten equilibrados en empleados
     * @param division
     * @param maxDiv
     */
    private void asignacionEquitativaDivision(Division division, Long maxDiv){
        List<Empleado> employWithoutDivision = buscarEmpleadosSinDivision();
        employWithoutDivision.stream().forEach(employ-> {
            if (totalEmpleadosDivision(division) < maxDiv){
                employ.assignDivision(division);
            }
        });
    }

    /////////////////////////////////////////////////////
    //
    //  `Parte opcional B)Asignación equitativa por departamento
    //
    ////////////////////////////////////////////////////
    /**
     *  Asignara departamento de forma equitativa todos los empleados sin departamento
     * */
    public void asignarEmpleadosDepartamento(Division division){
        Map<Departamento, Long> divisionLongMap = obtenerContadoresDepartamento(division);

        //Obtenemos el numero de empleados por dept maximo entre los departamentos
        Long maxDep = divisionLongMap.entrySet().stream().map(div -> div.getValue()).reduce((x, y) -> {
            if (x > y) return x;
            else return y;
        }).orElse(new Long(0));

        //Asignamos elementos hasta que todas los departamentos tengan el mismo numero de elementos
        Stream.of(Departamento.values()).forEach(dept -> {
            if(dept!= Departamento.DEPNA)
                asignacionEquiDepartamento(division,dept,maxDep);}
        );

        //El resto de empleados los dividimos entre los 3 tipos de departamento y los asignamos
        Long numEmployWithoutDep=(buscarEmpleadosSinDepartamento(division).size()/3)+maxDep;
        Stream.of(Departamento.values()).forEach(dept -> {
            if(dept!= Departamento.DEPNA)
            asignacionEquiDepartamento(division,dept,numEmployWithoutDep);});

        int lastEmploy= buscarEmpleadosSinDepartamento(division).size();
        Long totalEmploy= totalEmpleadoDeptDivision(division,Departamento.DEPSM);

        //Si aun queda algun empleado sin departamento lo añadimos en alguno de los departamentos.
        if(lastEmploy>0){
            asignacionEquiDepartamento(division,Departamento.DEPSM,new Long(totalEmploy+lastEmploy));
        }
    }

    /**
     * Metodo que nos permite asignar departamento hasta que todos esten equilibrados en empleados
     * @param div
     * @param maxDiv
     */
    private void asignacionEquiDepartamento(Division div, Departamento dept, Long maxDiv){
        List<Empleado> employWithoutDept = buscarEmpleadosSinDepartamento(div);
        employWithoutDept.stream().forEach(employ-> {
            if (totalEmpleadoDeptDivision(div,dept) < maxDiv){
                employ.assignDepartment(dept);
            }
        });
    }

    public long totalEmpleadoDeptDivision(Division division, Departamento dept){
        return obtenerContadoresDepartamento(division).get(dept);
    }
}

