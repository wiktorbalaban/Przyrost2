package hibernate.model;

import javax.persistence.*;

@Entity
@Table(name = "NICKNAME", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"id"})})
public class Nickname {

    @Id @GeneratedValue
    @Column(name = "id")
    private int id;

    @Column(name = "nickname")
    private String nickname;

    public Nickname() {}

    public int getId() {
        return id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
