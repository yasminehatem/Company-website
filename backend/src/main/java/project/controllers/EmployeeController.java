package project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.services.EmployeeService;
import project.models.Info;

import javax.validation.ConstraintViolationException;
import java.time.format.DateTimeParseException;
import java.util.Map;

@RestController
public class EmployeeController {

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<String> onValidationErrorConstraint(Exception ex) {
        return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(ArithmeticException.class)
    public ResponseEntity<String> onValidationErro(Exception ex) {
        return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<String> onValidationErrorDuplicate(Exception ex) {
        return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DateTimeParseException.class)
    public ResponseEntity<String> onValidationErrorDate(Exception ex) {
        return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
    }




    @Autowired
    private EmployeeService employeeService;


    @RequestMapping(value = "/Employees", method=RequestMethod.GET)
    public String getEmployees(){
        return  employeeService.getAllUsers();
    }




    @RequestMapping(value="/add", method=RequestMethod.POST)
    public Info addEmployer(@RequestBody Map<String,Object> maps) {

         return employeeService.addEmployer(maps);



    }




}







