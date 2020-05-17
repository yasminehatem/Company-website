package project.services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import project.models.Department;
import project.models.Employee;
import project.models.Info;
import project.models.Title;
import project.repositories.DepartmentRepository;
import project.repositories.EmployeeRepository;
import project.repositories.InfoRepository;
import project.repositories.TitleRepository;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {EmployeeService.class})
@TestPropertySource(locations = {"classpath:test.integration.properties"})
public class EmployeeServiceTests {

    @Autowired
    private EmployeeService employeeService;

    @MockBean
    private EmployeeRepository employeeRepository;

    @MockBean
    private InfoRepository infoRepository;

    @MockBean
    private TitleRepository titleRepository;

    @MockBean
    private DepartmentRepository departmentRepository;

    @Test
    public void testCalculateBirthDate() {
        LocalDate birthYear = LocalDate.parse(("1995-11-20"));
        LocalDate year = LocalDate.parse(("2018-11-20"));

        int age = employeeService.calculateAge(birthYear,year);

        assertEquals(age, 23);

    }

    @Test
    public void testCalculateBirthDateWithNullDates() {
        LocalDate birthYear = null;
        LocalDate year = null;

        int age = employeeService.calculateAge(birthYear,year);

        assertEquals(age, 0);

    }

@Test
    public void testIsValidEmail()
{
 boolean result=employeeService.isValidEmail("yasmineelbakry@gmail.com");
assertEquals(result,true);



}

    @Test
    public void testIsValidEmailFormat()
    {
        boolean result=employeeService.isValidEmail("yasmineelbakrygmail.com");
        assertEquals(result,false);

    }



    @Test
    public void testIsValidEmailFormat2()
    {
        boolean result=employeeService.isValidEmail("yasmineelbakry@gmailcom");
        assertEquals(result,false);

    }

    @Test
    public void testIsValidEmailEmpty()
    {
        boolean result=employeeService.isValidEmail("");
        assertEquals(result,false);

    }

    @Test
    public void testIsValidFullName()
    {
        boolean result=employeeService.isValidFullName("yasmine elbakry");
        assertEquals(result,true);

    }

    @Test
    public void testIsFullNameHasNumbers()
    {
        boolean result=employeeService.isValidFullName("yasmine elbakry1313");
        assertEquals(result,false);

    }

    @Test
    public void testIsFullNameEmpty()
    {
        boolean result=employeeService.isValidFullName("");
        assertEquals(result,false);

    }





    @Test
    public void testIsValidTitle()
    {
        boolean result=employeeService.isValidFTitle("HR Manager");
        assertEquals(result,true);

    }


    @Test
    public void testIsValidTitleHasNumbers()
    {
        boolean result=employeeService.isValidFTitle("768976");
        assertEquals(result,false);

    }

    @Test
    public void testIsValidTitleEmpty()
    {
        boolean result=employeeService.isValidFTitle("");
        assertEquals(result,false);

    }



@Test
    public void testAddEmployer()
{
    Map<String,Object> maps = new HashMap<>();
    maps.put("fullName","lijj oibjkhbh");
    maps.put("email","yas@hotmail.com");
    maps.put( "titleName","Translator");
    maps.put( "birthDate","2010-07-07");
    maps.put( "isLeader",false);

    Department dep = new Department();
    Long idDep = new Long(2);
    dep.setID(idDep);
    dep.setDepName("Production");

    String name = "Translator";
    Title t = new Title();
    t.setName("Translator");
    Long id = new Long(4);

    t.setID((id));
    t.setDepID(dep);


    given(titleRepository.findByName("Translator")).willReturn(t);

    Info info= employeeService.addEmployer(maps);


    //assertEquals(e.get(0).getFullName(),"lijj oibjkhbh");



}














}
