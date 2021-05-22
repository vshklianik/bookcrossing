package by.serhel.springwebapp.service;

import by.serhel.springwebapp.entities.types.Role;
import by.serhel.springwebapp.entities.User;
import by.serhel.springwebapp.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private BookService bookService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);

        if(user == null){
            throw new UsernameNotFoundException("User not found" );
        }

        return user;
    }

    public boolean addUser(User user){
        User userFromDb = userRepository.findByUsername(user.getUsername());
        if(userFromDb == null){
            user.setActive(true);
            user.setRoles(Collections.singleton(Role.USER));
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.save(user);
            return true;
        }
        return false;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public void save(User user){
        userRepository.save(user);
    }

    public void saveUser(User user,Map<String, String> form) {
        Set<String> roles = Arrays.stream(Role.values())
                .map(Role::name)
                .collect(Collectors.toSet());

        user.getRoles().clear();

        for(String key : form.keySet()){
            if(roles.contains(key)){
                user.getRoles().add(Role.valueOf(key));
            }
        }
        userRepository.save(user);
    }

    public void updateProfile(User user, String firstName, String lastName, String email, String password, String phoneNumber) {
        String userEmail = user.getEmail();

        if(email != null && !email.equals(userEmail) && email.matches("[\\w\\.%+-]+@\\w+\\.\\w{2,3}")){
            user.setEmail(email);
        }

        if(!StringUtils.isEmpty(firstName)){
            user.setFirstName(firstName);
        }

        if(!StringUtils.isEmpty(lastName)){
            user.setLastName(lastName);
        }

        if(!StringUtils.isEmpty(phoneNumber)){
            user.setPhoneNumber(phoneNumber);
        }

        if(!StringUtils.isEmpty(password)){
            user.setPassword(passwordEncoder.encode(password));
        }
        userRepository.save(user);
    }

    @Transactional
    public void deleteUser(User user) {
        bookService.deleteAllBooksByAuthor(user);
        userRepository.delete(user);
    }
}
