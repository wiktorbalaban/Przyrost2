package hibernate.model;

import javax.persistence.*;

@Entity
@Table(name = "WIFE", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"id"})})
public class Wife {

    @Id @GeneratedValue
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "percentageToPower")
    private int percentageToPower;

    public Wife() {
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getPercentageToPower() {
        return percentageToPower;
    }

    public void setPercentageToPower(int percentageToPower) {
        this.percentageToPower = percentageToPower;
    }
}
