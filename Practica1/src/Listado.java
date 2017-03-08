import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.CollationElementIterator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by LENOVO on 07/03/2017.
 */
public class Listado {
    /*+7

     */
    Map<String, Empleado> list;

    public Listado(String fileName)throws IOException{
        list =  new HashMap<String,Empleado>();
        Stream<String> lines= Files.lines(Paths.get(fileName));
        list= lines.map(line -> {
            return createEmpleado(line);
        }).
        collect(Collectors.
                toMap(employ -> employ.obtainDni(), Function.identity()));
    }

    /*
        DO
     */
    public void loadDepartment(String fileName)throws IOException{
        Stream<String> lines= Files.lines(Paths.get(fileName));

        //Obtener un hashmap de departamento
        List<String> departmentList = lines.collect(Collectors.toList());
        //I take the department and clean the name and the white space
        String department=departmentList.get(0);
        departmentList.remove(0);
        departmentList.remove(1);

        list.entrySet().stream().forEach(emp -> {
            if(departmentList.contains(emp.getKey()))
                emp.getValue().asignDepartment(Departamento.valueOf(department));
        });
    }

    /*
        DO
     */
    public void loadDivision(String fileName)throws IOException{
        Stream<String> lines= Files.lines(Paths.get(fileName));

        //Obtener un hashmap de division
        List<String> divisionList = lines.collect(Collectors.toList());
        //I take the department and clean the name and the white space
        String division=divisionList.get(0);
        divisionList.remove(0);
        divisionList.remove(1);

        list.entrySet().stream().forEach(emp -> {
            if(divisionList.contains(emp.getKey()))
                emp.getValue().asignDivision(Division.valueOf(division));
        });
    }

    public Map<Departamento, Long> obtainCountDepartment(){
         return list.entrySet().stream().collect(Collectors.groupingBy(
                list -> list.getValue().obtainDepartment()
                , Collectors.counting()));
    }

    /*public Map<Division, Map<Departamento, Long>> obtainCountDepartmentDivision(){
        list.entrySet().stream().collect(Collectors.groupingBy(
                (list, prueba) -> {
                    list.getValue().obtainDivision(), obtainCountDepartment()
                }
                , Collectors.counting()));
    }*/

    /**
     * Metodo para buscar los empleados sin division asignada: es decir,
     * en el dato miembro division tendran el valor DIVNA
     * */
    public List<Empleado> findEmployWithoutDivision(){
         return list.entrySet().stream().
                 filter(employ -> employ.getValue().obtainDivision() == Division.DIVNA).
                 map(employ -> employ.getValue()).collect(Collectors.toList());
    }

    public List<Empleado> findEmployWthDivDep(){
        return list.entrySet().stream().
                filter(employ -> employ.getValue().obtainDepartment() == Departamento.DEPNA &&
                        employ.getValue().obtainDivision() == Division.DIVNA).
                map(employ -> employ.getValue()).collect(Collectors.toList());
    }


    public List<Empleado> findEmployWithoutDepartment(Division division){
        return list.entrySet().stream().
                filter(employ ->  employ.getValue().obtainDivision()== division&&
                        employ.getValue().obtainDepartment() == Departamento.DEPNA).
                map(employ -> employ.getValue()).collect(Collectors.toList());
    }

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
        //poner en el empleado como asignado a dpetNA y divNA
    }

    public static void main(String[] args) throws IOException {
        Listado prueba=new Listado("./data/datos.txt");
        prueba.loadDepartment("./data/asignacionDEPSA.txt");
        prueba.loadDepartment("./data/asignacionDEPSB.txt");
        prueba.loadDepartment("./data/asignacionDEPSM.txt");

        prueba.loadDivision("./data/asignacionDIVID.txt");
        prueba.loadDivision("./data/asignacionDIVHW.txt");
        prueba.loadDivision("./data/asignacionDIVSER.txt");
        prueba.loadDivision("./data/asignacionDIVSW.txt");
        System.out.println(prueba.toString());
    }
}
