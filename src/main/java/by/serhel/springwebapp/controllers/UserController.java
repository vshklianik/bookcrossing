package by.serhel.springwebapp.controllers;

import by.serhel.springwebapp.entities.Role;
import by.serhel.springwebapp.entities.User;
import by.serhel.springwebapp.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/users")
public class UserController {
    private static Logger logger = LogManager.getLogger(UserController.class.getName());

    @Autowired
    private UserService userService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping
    public String userList(Model model){
        logger.info("start 'userList'");

        model.addAttribute("users", userService.findAll());

        logger.info("finish 'userList'");
        return "userList";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/edit/{user}")
    public String userEditForm(@PathVariable User user, Model model){
        logger.info("start 'userEditForm'");

        model.addAttribute("user", user);
        model.addAttribute("roles", Role.values());

        logger.info("finish 'userEditForm'");
        return "userEdit";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/delete/{user}")
    public String userDelete(@PathVariable User user, Model model){
        logger.info("start 'userDelete'");

        if(userService.deleteUser(user)){
            model.addAttribute("message", "user deleted");
        }
        model.addAttribute("message", "user not deleted");

        logger.info("finish 'userDelete'");
        return "redirect:/users";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/edit")
    public String userSave(@RequestParam String username,
            @RequestParam Map<String, String> form,
            @RequestParam("userId") User user)
    {
        logger.info("start 'userSave'");

        userService.saveUser(user, username, form);

        logger.info("finish 'userSave'");
        return "redirect:/users";
    }

    @GetMapping("/profile")
    public String getProfile(Model model, @AuthenticationPrincipal User user){
        logger.info("start 'getProfile'");

        model.addAttribute("username", user.getUsername());
        model.addAttribute("email", user.getEmail());
        model.addAttribute("phoneNumber", user.getPhoneNumber());

        logger.info("finish 'getProfile'");
        return "profile";
    }

    @PostMapping("/profile")
    public String updateProfile(@AuthenticationPrincipal User user,
                                @RequestParam String email,
                                @RequestParam String password,
                                @RequestParam String phoneNumber
    ){
        logger.info("start 'updateProfile'");

        userService.updateProfile(user, email, password, phoneNumber);

        logger.info("finish 'updateProfile'");
        return "redirect:/users/profile";
    }
}
