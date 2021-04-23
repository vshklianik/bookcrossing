package by.serhel.springwebapp.service;

import by.serhel.springwebapp.entities.User;
import by.serhel.springwebapp.entities.types.Role;
import by.serhel.springwebapp.repositories.IUserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;


@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource("/application-test.properties")
@Sql(value = {"/initialize-user-before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = {"/initialize-user-after.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class UserServiceTest {
    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder encoder;
    private User user;

    @Before
    public void init() {
        user = new User();
        user.setId(3l);
        user.setUsername("testUser");
        user.setPassword("testPass");
        user.setFirstName("test");
        user.setLastName("test");
        user.setEmail("test@mail.ru");
        Set<Role> roles = new HashSet<>();
        roles.add(Role.USER);
        user.setRoles(roles);
    }

    @Test
    public void loadUserByUsername_UserExists_UserReturn() {
        User excepted = userRepository.findByUsername("admin");
        User actual = (User) userService.loadUserByUsername("admin");
        assertEquals(excepted, actual);
    }

    @Test(expected = UsernameNotFoundException.class)
    public void loadUserByUsername_UserNotExists_ThrowUsernameNotFoundException() {
        User actual = (User) userService.loadUserByUsername("");
    }

    @Test
    public void addUser_UserNotExists_True() {
        boolean actual = userService.addUser(user);
        assertTrue(actual);
    }

    @Test
    public void addUser_UserExists_False() {
        User user = new User();
        user.setUsername("admin");
        boolean actual = userService.addUser(user);
        assertFalse(actual);
    }

    @Test
    public void saveUser_EmptyForm_RoleEqualsUSER() {
        userService.saveUser(user, new HashMap<>());
        Set<Role> actual = userRepository.findByUsername("testUser").getRoles();
        assertEquals(user.getRoles(), actual);
    }

    @Test
    public void saveUser_FormContainADMIN_RoleEqualsUSERAndADMIN() {
        userService.saveUser(user, new HashMap<String, String>() {{
            put("ADMIN", null);
        }});
        Set<Role> actual = userRepository.findByUsername("testUser").getRoles();
        user.getRoles().add(Role.ADMIN);
        assertEquals(user.getRoles(), actual);
    }

    @Test
    public void updateProfile_EmailIsNotNull_ActivationCodeNotNull() {
        userService.updateProfile(user, null, null,
                "testmail@lol.kek", null, null);
        User user1 = userRepository.findByUsername(user.getUsername());
        assertNotNull(user1.getActivationCode());
    }

    @Test
    public void updateProfile_firstNameEqualTestName_FirstNameUpdated() {
        String expected = "TestName";
        userService.updateProfile(user, expected, null,
                null, null, null);
        String actual = userRepository.findByUsername(user.getUsername()).getFirstName();
        assertEquals(expected, actual);
    }

    @Test
    public void updateProfile_LastNameEqualLast_LastNameUpdated() {
        String expected = "Last";
        userService.updateProfile(user, null, expected,
                null, null, null);
        String actual = userRepository.findByUsername(user.getUsername()).getLastName();
        assertEquals(expected, actual);
    }

    @Test
    public void updateProfile_PasswordEqualsTestPass_PasswordUpdated() {
        String pass = "TestPass";
        String tet = encoder.encode(pass);
        userService.updateProfile(user, null, null,
                null, pass, null);
        String encodedPass = userRepository.findByUsername(user.getUsername()).getPassword();
        assertTrue(encoder.matches(pass, encodedPass));
    }

    @Test
    public void updateProfile_NewPhoneNumber_PhoneNumberUpdated() {
        String expected = "+375291667715";
        userService.updateProfile(user, null, null,
                null, null, expected);
        String actual = userRepository.findByUsername(user.getUsername()).getPhoneNumber();
        assertEquals(expected, actual);
    }
}