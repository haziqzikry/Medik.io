package project.oobat.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity // This tells Hibernate to make a table out of this class
@Table(name = "users") // This tells Hibernate to name the table "users"
@Data // This tells Lombok to generate getters and setters
@NoArgsConstructor // This tells Lombok to generate a no-args constructor
@AllArgsConstructor // This tells Lombok to generate an all-args constructor
public class AppUser {

    public static enum Role {
        PHARMACIST("PHARMACIST"),
        CUSTOMER("CUSTOMER");

        private String role;

        private Role(String role) {
            this.role = role;
        }

        public String getRole() {
            return role;
        }
    }

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;
    private String address;
    private String phone;

    @Enumerated(EnumType.STRING)
    private Role role;
    
}
