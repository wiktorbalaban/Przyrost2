package hibernate.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "WARRIOR", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"id"})})
public class Warrior {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "NICKNAME_ID", referencedColumnName = "id")
    Nickname nickname;

    @Column(name = "power")
    private int power;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "FIGHTING_SCHOOL_ID", referencedColumnName = "id")
    private FightingSchool fightingSchool;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Technique> techniques = new ArrayList<>();

    public Warrior() {
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

    public Nickname getNickname() {
        return nickname;
    }

    public void setNickname(Nickname nickname) {
        this.nickname = nickname;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public List<Technique> getTechniques() {
        return techniques;
    }

    public void setTechniques(List<Technique> techniques) {
        this.techniques = techniques;
    }

    public FightingSchool getFightingSchool() {
        return fightingSchool;
    }

    public void setFightingSchool(FightingSchool fightingSchool) {
        this.fightingSchool = fightingSchool;
    }
}
