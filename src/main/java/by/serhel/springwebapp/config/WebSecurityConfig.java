package by.serhel.springwebapp.config;

import by.serhel.springwebapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserService userService;

    @Autowired
    private  PasswordEncoder passwordEncoder;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                    .mvcMatchers("/", "/registration", "/static/**", "/activate/* ").permitAll()
                    .anyRequest().authenticated()
                .and()
                    .formLogin()
                    .loginPage("/login")
                    .permitAll()
                .and()
                    .rememberMe()
                .and()
                    .logout()
                    .permitAll()
                .and()
                .csrf().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService)
                .passwordEncoder(passwordEncoder);
    }

//        @Bean
//    public PrincipalExtractor principalExtractor(IUserRepository userRepository) {
//        return map -> {
//            String str = (String)map.get("sub");
//            Long id = Long.parseLong(str.substring(0,10));
//            User user = userRepository.findById(id).orElseGet(() -> {
//                User newUser = new User();
//
//                newUser.setId(id);
//                newUser.setFirstName((String) map.get("given_name"));
//                newUser.setLastName((String) map.get("family_name"));
//                newUser.setEmail((String) map.get("email"));
//                String username = (String) map.get("email");
//                newUser.setUsername(username.substring(0, username.indexOf("@")));
//
//                return newUser;
//            });
//            user.setActive(true);
//            Set<Role> roles = new HashSet<>();
//            roles.add(Role.USER);
//            user.setRoles(roles);
//            return userRepository.save(user);
//        };
//    }
}
