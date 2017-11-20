package hibernate.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "TOURNAMENT", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"id"})})
public class Tournament {

    @Id @GeneratedValue
    @Column(name = "id")
    private int id;

    @Column(name = "tournament")
    private String tournament;

    @Column(name = "winner")
    private String winner;

    @Column(name = "date")
    private LocalDate date;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="ARENA_ID", referencedColumnName = "id")
    private Arena arena;

    public Tournament() {
    }

    public int getId() {
        return id;
    }

    public String getTournament() {
        return tournament;
    }

    public void setTournament(String tournament) {
        this.tournament = tournament;
    }

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Arena getArena() {
        return arena;
    }

    public void setArena(Arena arena) {
        this.arena = arena;
    }
}
