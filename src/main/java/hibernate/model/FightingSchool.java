package hibernate.model;

import javax.persistence.*;

@Entity
@Table(name = "FIGHTING_SCHOOL", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"id"})})
public class FightingSchool {

    @Id @GeneratedValue
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "percentageToPower")
    private int percentageToPower;

    public FightingSchool() {
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

    public int getPercentageToPower() {
        return percentageToPower;
    }

    public void setPercentageToPower(int percentageToPower) {
        this.percentageToPower = percentageToPower;
    }
}
