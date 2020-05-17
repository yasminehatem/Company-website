package project.models;

import javax.persistence.*;

@Entity
@Table(name = "Info")

public class Info {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long    ID;

    @OneToOne
    @JoinColumn(name = "employeeID")
    private Employee employeeID;

    @Column(name = "isLeader")
    private boolean isLeader;

    
    @ManyToOne
    @JoinColumn(name = "titleID")
    private Title titleID;


    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public Employee getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(Employee employeeID) {
        this.employeeID = employeeID;
    }

    public boolean isLeader() {
        return isLeader;
    }

    public void setLeader(boolean leader) {
        isLeader = leader;
    }

    public Title getTitleID() {
        return titleID;
    }

    public void setTitleID(Title titleID) {
        this.titleID = titleID;
    }
}