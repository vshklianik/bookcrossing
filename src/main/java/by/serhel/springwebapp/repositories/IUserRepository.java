package by.serhel.springwebapp.repositories;

import by.serhel.springwebapp.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    User findByActivationCode(String code);
}
