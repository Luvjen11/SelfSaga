package jennifer.SelfSaga.User;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String email;
    private String password;
    private String username;
    private Integer xp = 0;
    private Integer level =1;

    public User() {
       //empty constructor
    } 

    public User(String email, String password, String username) {

        this.email = email;
        this.password = password;
        this.username = username;
    }

    public Long getid() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getXp() {
        return xp;
    }

    public void setXp(Integer xp) {
        this.xp = xp;
    }

    public Integer getlevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }
}
