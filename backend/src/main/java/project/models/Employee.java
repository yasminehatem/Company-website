package project.models;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "Employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long ID;



    @NotNull(message="full name not null")
    @Pattern(regexp="^([A-Za-z ]*$)",message="full name not in the correct format")
    @Column(name = "fullName")
    private String fullName;



    @Column(name = "email",unique = true)
    private String email;


    @Column(name = "age")
    private Long age;


    //@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Column(name = "birthDate")
    private LocalDate birthDate;


    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getAge() {
        return age;
    }

    public void setAge(Long age) {
        this.age = age;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }
}



