package by.serhel.springwebapp.service;

import by.serhel.springwebapp.entities.Role;
import by.serhel.springwebapp.entities.User;
import by.serhel.springwebapp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.UUID;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MailSender mailSender;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username);
    }

    public boolean addUser(User user){
        User userFromDb = userRepository.findByUsername(user.getUsername());
        if(userFromDb == null){
            user.setActive(true);
            user.setRoles(Collections.singleton(Role.USER));
            user.setActivationCode(UUID.randomUUID().toString());

            userRepository.save(user);

            if(!StringUtils.isEmpty(user.getEmail())){
                String message = String.format(
                        "Hello %s!\n" +
                                "Welcome to BookCrossing. Please, visit next link: http://localhost:8080/activate/%s",
                        user.getUsername(), user.getActivationCode());

                mailSender.send(user.getEmail(), "Activation code", message);
            }
            return true;
        }
        return false;
    }

    public boolean activateUser(String code) {
        User user = userRepository.findByActivationCode(code);
        if(user == null){
            return false;
        }
        user.setActivationCode(null);
        userRepository.save(user);

        return true;
    }
}
