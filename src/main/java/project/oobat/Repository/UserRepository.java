package project.oobat.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import project.oobat.Model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    
}
