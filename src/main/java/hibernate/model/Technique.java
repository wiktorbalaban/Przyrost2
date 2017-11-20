package hibernate.model;

import javax.persistence.*;

@Entity
@Table(name = "TECHNIQUE", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"technique"})})
public class Technique {

    @Id @GeneratedValue
    @Column(name = "id")
    private int id;

    @Column(name = "technique")
    private String technique;

    @Column(name = "percentageToPower")
    private int percentageToPower;

    public Technique() {
    }

    public int getId() {
        return id;
    }

    public String getTechnique() {
        return technique;
    }

    public void setTechnique(String technique) {
        this.technique = technique;
    }

    public int getPercentageToPower() {
        return percentageToPower;
    }

    public void setPercentageToPower(int percentageToPower) {
        this.percentageToPower = percentageToPower;
    }
}
