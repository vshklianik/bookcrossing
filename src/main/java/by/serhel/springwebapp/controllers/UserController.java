package by.serhel.springwebapp.controllers;

import by.serhel.springwebapp.entities.types.Role;
import by.serhel.springwebapp.entities.User;
import by.serhel.springwebapp.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
        userService.deleteUser(user);
        logger.info("finish 'userDelete'");
        return "redirect:/users";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/edit")
    public String userSave(@RequestParam Map<String, String> form,
            @RequestParam("userId") User user)
    {
        logger.info("start 'userSave'");
        userService.saveUser(user, form);
        logger.info("finish 'userSave'");
        return "redirect:/users";
    }

    @GetMapping("/profile")
    public String getProfile(Model model, @AuthenticationPrincipal User user){
        logger.info("start 'getProfile'");
        model.addAttribute("user", user);
        logger.info("finish 'getProfile'");
        return "profile";
    }

    @PostMapping("/profile")
    public String updateProfile(@AuthenticationPrincipal User currentUser,
                                @Valid User validatingUser,
                                BindingResult result,
                                Model model
    ) {
        logger.info("start 'updateProfile'");
        Map<String, String> errors = null;
        if (result.hasErrors()) {
            errors = ControllerUtils.getErrors(result);
            errors.remove("passwordError");
        }
        if (errors != null && errors.size() > 0){
            model.mergeAttributes(errors);
            model.addAttribute(validatingUser);
            return "profile";
        }
        userService.updateProfile(currentUser, validatingUser);
        logger.info("finish 'updateProfile'");
        return "redirect:/users/profile";
    }
}
