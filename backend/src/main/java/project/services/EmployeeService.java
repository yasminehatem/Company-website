package project.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.models.Department;
import project.models.Employee;
import project.models.Info;
import project.models.Title;
import project.repositories.DepartmentRepository;
import project.repositories.EmployeeRepository;
import project.repositories.InfoRepository;
import project.repositories.TitleRepository;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.TemporalAccessor;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;


@Service
public class EmployeeService {



    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private InfoRepository infoRepository;

    @Autowired
    private TitleRepository titleRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    public int calculateAge(LocalDate birthDate, LocalDate currentDate) {
        if ((birthDate != null) && (currentDate != null)) {
            return Period.between(birthDate, currentDate).getYears();
        } else {
            return 0;
        }

    }
    ///////////////////////////////CHECKS/////////////////////
    public static boolean isValidEmail(String email)
    {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        if (email == "")
            return false;
        return pat.matcher(email).matches();
    }

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("[yyyy-MM-dd]");

    static TemporalAccessor parseDate(String dateAsString) {
        return FORMATTER.parseBest(dateAsString, LocalDate::from, YearMonth::from, Year::from);
    }

    public static boolean isValidDate(String dateAsString) {
      if(dateAsString==null) return  false;
      LocalDate currentDate = LocalDate.now();
        if (LocalDate.parse(dateAsString).compareTo(currentDate) > 0)
        {System.out.println("inv date");
        return false;
        }
        try {
            parseDate(dateAsString);
            return true;
        } catch (DateTimeParseException e) {
            return false;

        }
    }

  public static boolean isValidFullName( String lastName ) {
      return lastName.matches( "^([A-Za-z ]*$)" ) && lastName.length()<70 && lastName!=null &&lastName!="";
  }

    public static boolean isValidFTitle( String titlename ) {
        return titlename.matches( "^([A-Za-z ]*$)" ) && titlename.length()<50 && titlename!=null &&titlename!="";
    }
  //return lastName.matches( "[A-Z]+([ '-][a-zA-Z]+)*" ) && lastName.length()<70 && lastName!=null;
  ///////////////////////////////CHECKS END/////////////////////


    public String getAllUsers() {
        List<Info> listOfInfos = infoRepository.findAll();
         Employee e = new Employee();
         Title t = new Title();
         Department dep = new Department();

        for (int i = 0 ; i < listOfInfos.size(); i++) {

            e = listOfInfos.get(i).getEmployeeID();
            t = listOfInfos.get(i).getTitleID();
            dep = t.getDepID();

            return "";


        }
        return "";




   }



    public Info addEmployer(Map<String,Object> maps)  {

        //String depName = maps.get("depName").toString();
        Employee e = new Employee();
        Boolean isLeader = false;
        Title title = null;
        Department dep = null;
        Info info = new Info();
        String birth = "";
        if ((maps.get("fullName") != null) && (maps.get("titleName") != null) && (maps.get("email") != null) && (maps.get("isLeader") != null)

              && (maps.get("birthDate") != null) ) {

            String titleName = maps.get("titleName").toString();
            System.out.print(titleRepository.findByName(titleName));
             if(titleRepository.findByName(titleName) != null){
             title = titleRepository.findByName(titleName);
             dep = title.getDepID();
             }else{
                 System.out.print("inn inn");
                 throw new ArithmeticException("Title does not exist");
                 }




            birth = maps.get("birthDate").toString();
            e.setFullName(maps.get("fullName").toString());
            e.setEmail(maps.get("email").toString());
            e.setBirthDate(LocalDate.parse((birth)));

            info = new Info();
            info.setLeader((boolean) maps.get("isLeader"));
            isLeader = (boolean) maps.get("isLeader");

         }
//        System.out.println("email" + isValidEmail(e.getEmail()));
//        System.out.println( e.getFullName());
//        System.out.println(" name " + isValidFullName(e.getFullName()));
//        System.out.println("date " + isValidDate((maps.get("birthDate").toString())));

        if( !isValidEmail(e.getEmail()) ) {throw new ArithmeticException("email invalid"); }
        else if( !isValidFullName(e.getFullName())  ) {throw new ArithmeticException("fullName wrong"); }
        else if( !isValidFTitle(title.getName())  ) {throw new ArithmeticException("title wrong"); }
       else if (!isValidDate(birth) ) {throw new ArithmeticException("birthDate wrong"); }
        else{

            LocalDate birthDate = e.getBirthDate();
            LocalDate currentDate = LocalDate.now();
            long age = this.calculateAge(birthDate,currentDate);
            e.setAge(age);
            e = employeeRepository.save(e);
            if (isLeader && dep.getEmployee() == null) {
                //departmentRepository.delete(dep);
                dep.setEmployee(e);
                //title.setDepID(dep);
                title.setDepID(departmentRepository.save(dep));
//                Title title2 = new Title();
//                title2.setDepID(title.getDepID());
//                title2.setName(title.getName());
                info.setEmployeeID(e);
                info.setTitleID(title);
                return infoRepository.save(info);
//            info.setTitleID(titleRepository.save(title));
            }
            else {
                //dep.setEmployee(e);
                info.setEmployeeID(e);
                System.out.println(title.getID());
                //title.setID(title.getID());
                info.setTitleID(titleRepository.save(title));
                return infoRepository.save(info);
            }
        }

    }
}
