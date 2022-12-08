package project.oobat.Service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import project.oobat.Model.User;
import project.oobat.Repository.UserRepository;

@Service // This tells Spring to make a bean out of this class
@RequiredArgsConstructor // This tells Lombok to generate a constructor with all final fields
@Transactional // This tells Spring to wrap all methods in a transaction
@Slf4j // This tells Lombok to generate a logger
public class UserService {
    
}
