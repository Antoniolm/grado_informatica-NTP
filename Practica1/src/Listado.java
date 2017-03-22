import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.CollationElementIterator;
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
     * It will assign a department to each employ
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
     * It will assign a division to each employ
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
     * It will return the number of employees in our list
     * */
    public int obtainNumberEmploy(){
        long result = list.entrySet().stream().map(employ -> employ.getValue()).count();
        return (int)result;
    }

    /**
     * It will return the number of employees in each department in a division
     * */
    public Map<Departamento, Long> obtainCountDepartment(Division aDivision){
         return list.entrySet().stream().filter(employ -> employ.getValue().obtainDivision()==aDivision).
                 map(employ -> employ.getValue().obtainDepartment()).
                 sorted(Comparator.naturalOrder()).
                 collect(Collectors.groupingBy(Function.identity(),
                 TreeMap::new,
                 Collectors.counting()));
    }

    /**
     *  It will return the number of employees in each division
     * */
    public Map<Division, Map<Departamento, Long>> obtainCountDepartmentDivision(){
        Map<Division, Map<Departamento, Long>> mapa=new TreeMap<>();
        Stream.of(Division.values()).
                forEach(division -> mapa.put(division,obtainCountDepartment(division)));
        return mapa;
    }


    /**
     * Metodo para buscar los empleados sin division asignada: es decir,
     * en el dato miembro division tendran el valor DIVNA
     * */
    public List<Empleado> findEmployWithoutDivision(){
         return list.entrySet().stream().
                 filter(employ -> employ.getValue().obtainDivision() == Division.DIVNA).
                 map(employ -> employ.getValue()).collect(Collectors.toList());
    }

    /**
     *  It will find the employees with division and without department
     * */
    public List<Empleado> findEmployWthDivDep(){
        return list.entrySet().stream().
                filter(employ -> employ.getValue().obtainDepartment() == Departamento.DEPNA &&
                        employ.getValue().obtainDivision() != Division.DIVNA).
                map(employ -> employ.getValue()).collect(Collectors.toList());
    }

    /**
     *  It will find the employees without department
     * */
    public List<Empleado> findEmployWithoutDepartment(Division division){
        return list.entrySet().stream().
                filter(employ ->  employ.getValue().obtainDivision()== division&&
                        employ.getValue().obtainDepartment() == Departamento.DEPNA).
                map(employ -> employ.getValue()).collect(Collectors.toList());
    }

    /**
     *  It will find the employ with a repeated dni
     * */
    public boolean hasDniRepeated(){
        boolean result=false;
        Map<String, Long> mailList = list.entrySet().stream().collect(Collectors.groupingBy(
                employ -> employ.getValue().obtainDni(),
                Collectors.counting()));

        List<String> mailRepeated = mailList.entrySet().stream().filter(dni -> dni.getValue() > 1)
                .map(dni -> dni.getKey())
                .collect(Collectors.toList());

        if(mailRepeated.size()>0)
            result=true;

        return result;
    }

    /**
     *  It will return the employ with a repeated dni
     * */
    public Map<String,List<Empleado>> obtainDniRepeated(){
        Map<String, Long> dniList = list.entrySet().stream().collect(Collectors.groupingBy(
                employ -> employ.getValue().obtainDni(),
                Collectors.counting()));

        List<String> dniRepeated = dniList.entrySet().stream().filter(dni -> dni.getValue() > 1)
                .map(dni -> dni.getKey())
                .collect(Collectors.toList());

        List<Empleado> employDni = list.entrySet().stream().
                filter(employ -> dniRepeated.contains(employ.getValue().obtainDni())).
                map(employ -> employ.getValue()).
                collect(Collectors.toList());
        Map<String, List<Empleado>> result = employDni.stream().collect(Collectors.groupingBy(
                employ -> employ.obtainDni()
                ));

        return result;
    }

    /**
     *  It will return if the list has employees with repeated email
     * */
    public boolean hasMailRepeated(){
        boolean result=false;
        Map<String, Long> mailList = list.entrySet().stream().collect(Collectors.groupingBy(
                employ -> employ.getValue().obtainMail(),
                Collectors.counting()));

        List<String> mailRepeated = mailList.entrySet().stream().filter(mail -> mail.getValue() > 1)
                .map(mail -> mail.getKey())
                .collect(Collectors.toList());

        if(mailRepeated.size()>0)
            result=true;

        return result;
    }


    /**
     *  It will return the employees that have a repeated email
     * */
    public Map<String,List<Empleado>> obtainMailRepeated(){
        Map<String, Long> mailList = list.entrySet().stream().collect(Collectors.groupingBy(
                employ -> employ.getValue().obtainMail(),
                Collectors.counting()));

        List<String> mailRepeated = mailList.entrySet().stream().filter(mail -> mail.getValue() > 1)
                .map(mail -> mail.getKey())
                .collect(Collectors.toList());

        List<Empleado> employEmail = list.entrySet().stream().
                filter(employ -> mailRepeated.contains(employ.getValue().obtainMail())).
                map(employ -> employ.getValue()).
                collect(Collectors.toList());
        Map<String, List<Empleado>> result = employEmail.stream().collect(Collectors.groupingBy(
                employ -> employ.obtainMail()
        ));

        return result;
    }


    /**
     *  It will return a string with all elements of our list
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
     *  It will return a string with all elements of our list
     * */
    public void assignEmployWithoutDivision(){
        Map<Division, Long> divisionLongMap = obtainEmpPerDiv();

        Long maxDiv = divisionLongMap.entrySet().stream().map(div -> div.getValue()).reduce((x, y) -> {
            if (x > y) return x;
            else return y;
        }).orElse(new Long(0));

        Stream.of(Division.values()).forEach(div -> {
            if(div!= Division.DIVNA)
                assignEmployDivisionIquality(div,maxDiv);
        });

        Long numEmployWithoutDiv=(findEmployWithoutDivision().size()/4)+maxDiv;
        Stream.of(Division.values()).forEach(div -> {
            if(div!= Division.DIVNA)
                assignEmployDivisionIquality(div,numEmployWithoutDiv);
        });

        System.out.println(" Division -"+obtainNumberEmployDiv(Division.DIVHW));
        System.out.println(" Division -"+obtainNumberEmployDiv(Division.DIVID));
        System.out.println(" Division -"+obtainNumberEmployDiv(Division.DIVSER));
        System.out.println(" Division -"+obtainNumberEmployDiv(Division.DIVSW));
    }

    /**
     * Metodo encargado de obtener el numero total de empleados en una division
     * @param division
     * @return
     */
    private long obtainNumberEmployDiv(Division division){
        Map<Departamento, Long> departamentoLongMap = obtainCountDepartment(division);
        return departamentoLongMap.entrySet().stream().map(depart -> depart.getValue()).
                reduce((x, y) -> x+ y).orElse(new Long(0));
    }

    /**
     * Metodo que obtiene un map de divisiones y para cada division se guarda el número total
     * empleados en esa division
     * @return
     */
    private Map<Division,Long> obtainEmpPerDiv(){
        Map<Division,Long> result=new TreeMap<Division,Long>();
        Stream.of(Division.values()).forEach(division -> result.put(division,obtainNumberEmployDiv(division)));

        return result;
    }

    /**
     * Metodo que nos permite asignar division hasta que todos esten equilibrados en empleados
     * @param division
     * @param maxDiv
     */
    private void assignEmployDivisionIquality(Division division,Long maxDiv){
        List<Empleado> employWithoutDivision = findEmployWithoutDivision();
        employWithoutDivision.stream().forEach(employ-> {
            if (obtainNumberEmployDiv(division) < maxDiv){
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
     *  It will return a string with all elements of our list
     * */
    public void assignEmployWithoutDept(Division division){
        Map<Departamento, Long> divisionLongMap = obtainCountDepartment(division);

        Long maxDep = divisionLongMap.entrySet().stream().map(div -> div.getValue()).reduce((x, y) -> {
            if (x > y) return x;
            else return y;
        }).orElse(new Long(0));

        Stream.of(Departamento.values()).forEach(dept -> {
            if(dept!= Departamento.DEPNA)
                assignEmployDeptIquality(division,dept,maxDep);}
        );

        Long numEmployWithoutDep=(findEmployWithoutDivision().size()/3)+maxDep;
        Stream.of(Departamento.values()).forEach(dept -> {
            if(dept!= Departamento.DEPNA)
            assignEmployDeptIquality(division,dept,numEmployWithoutDep);});

        System.out.println(obtainNumberEmployDept(Division.DIVSER,Departamento.DEPSB));
        System.out.println(obtainNumberEmployDept(Division.DIVSER,Departamento.DEPSM));
        System.out.println(obtainNumberEmployDept(Division.DIVSER,Departamento.DEPSA));
    }

    /**
     * Metodo que nos permite asignar departamento hasta que todos esten equilibrados en empleados
     * @param div
     * @param maxDiv
     */
    private void assignEmployDeptIquality(Division div,Departamento dept,Long maxDiv){
        List<Empleado> employWithoutDept = findEmployWithoutDepartment(div);
        employWithoutDept.stream().forEach(employ-> {
            if (obtainNumberEmployDept(div,dept) < maxDiv){
                employ.assignDepartment(dept);
            }
        });
    }

    private long obtainNumberEmployDept(Division division,Departamento dept){
        return obtainCountDepartment(division).get(dept);
    }
}

