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

        String department=lines.findFirst().get();
        List<String> departmentList = Files.lines(Paths.get(fileName)).skip(2).collect(Collectors.toList());

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

        String division=lines.findFirst().get();
        List<String> divisionList = Files.lines(Paths.get(fileName)).skip(2).collect(Collectors.toList());

        list.entrySet().stream().forEach(emp -> {
            if(divisionList.contains(emp.getKey()))
                emp.getValue().asignDivision(Division.valueOf(division));
        });

    }

    public Map<Departamento, Long> obtainCountDepartment(Division aDivision){
         return list.entrySet().stream().filter(employ -> employ.getValue().obtainDivision()==aDivision).
                 collect(Collectors.groupingBy(employ -> employ.getValue().obtainDepartment(),
                 Collectors.counting()));
    }

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

        result.entrySet().stream().forEach(employ -> System.out.println(employ.getValue().toString()));
        return result;
    }

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

        result.entrySet().stream().forEach(employ -> System.out.println(employ.getValue().toString()));
        return result;
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
}
