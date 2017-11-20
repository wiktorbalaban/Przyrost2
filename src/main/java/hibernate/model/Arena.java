package hibernate.model;

import javax.persistence.*;

@Entity
@Table(name = "WIFE", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"id"})})
public class Arena {

    @Id @GeneratedValue
    @Column(name = "id")
    private int id;

    @Column(name = "arena")
    private String arena;

    public int getId() {
        return id;
    }

    public String getArena() {
        return arena;
    }

    public void setArena(String arena) {
        this.arena = arena;
    }
}
