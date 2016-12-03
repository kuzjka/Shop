package ua.kiev.prog.model;

import javax.persistence.*;

@Entity
@Table(name="user_roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_role_id")
    private int id;
    private String username;
    private  String role;



    public Role() {
    }

    public Role(String username, String role) {
        this.username = username;
        this.role = role;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
