package domain;


import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
@Entity
@Table(name = "manufacture")
public class Manufacture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",nullable = false)
    private Integer id;
    @Column(name="name",nullable = false)
    private String name;
    @Column(name="location",nullable = false)
    private String location;
    @Column(name="employee",nullable = false)
    private Integer employee;
    @OneToMany(mappedBy = "",cascade = CascadeType.ALL)
    @JoinColumn(name = "manufacture_id")
    private Set<Phone> phoneSet;
    public Manufacture(String name, String location, Integer employee) {
        this.name = name;
        this.location = location;
        this.employee = employee;
        this.phoneSet = new HashSet<>();
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getEmployee() {
        return employee;
    }

    public void setEmployee(Integer employee) {
        this.employee = employee;
    }

    public Set<Phone> getItems(){
        return phoneSet;
    }

    public void setItems(Set<Phone> phoneSet){
        this.phoneSet = phoneSet;
    }

    public String toString() {
        return "Manufacturer name: " + name + ", location: " + location + ", number of employees: " +
                employee + ", ID: " + id;
    }

}
