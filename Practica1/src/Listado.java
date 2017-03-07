import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
                emp.getValue().asignDepartment(Departamento.DEPSA);
        });
        //1-Crear nuevo flujo de list
        //2-filtramos y buscamos su dni
        //3-mapeamos (x -> x.asignDepartment();

    }

    /*
        DO
     */
    public void loadDivision(String fileName)throws IOException{
        Stream<String> lines= Files.lines(Paths.get(fileName));
    }

    public void print(){
        list.forEach((dni, emp) -> {
                    System.out.println(emp.toString());
                }
        );
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
        prueba.print();
    }
}
