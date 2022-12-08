package project.oobat.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity // This tells Hibernate to make a table out of this class
@Data // This tells Lombok to generate getters and setters
@NoArgsConstructor // This tells Lombok to generate a no-args constructor
@AllArgsConstructor // This tells Lombok to generate an all-args constructor
public class User {

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
    private String password;
    private String address;
    private String phone;

    @Enumerated
    private Role role;
    
}
