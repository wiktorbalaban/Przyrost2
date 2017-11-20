package hibernate.model;

import javax.persistence.*;

@Entity
@Table(name = "WIFE", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"id"})})
public class Arena {

    @Id @GeneratedValue
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
