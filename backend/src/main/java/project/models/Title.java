
package project.models;

import project.models.Department;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "Title")
public class Title {

    @Id
    //@GeneratedValue
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long ID;

    @NotNull(message="title name not null")
    @Pattern(regexp="^([A-Za-z ]*$)",message="title name not in the correct format")
    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "depID")
    private Department depID;

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Department getDepID() {
        return depID;
    }

    public void setDepID(Department depID) {
        this.depID = depID;
    }
}


