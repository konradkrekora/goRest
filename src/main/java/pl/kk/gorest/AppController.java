package pl.kk.gorest;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pl.kk.gorest.registration.Data;
import pl.kk.gorest.registration.UserService;
import pl.kk.gorest.user.User;

import java.util.List;

@Controller
@AllArgsConstructor
public class AppController {

    private UserService userService;

    @GetMapping("/")
    public String showHomePage() {
        return userService.showHomePage();
    }

    @GetMapping(value = "/register_user")
    public String showRegisterForm(Model model) {
        model.addAttribute("user", new User());
        return "register_user";
    }

    @PostMapping (value = "/user_success")
    public String addGoRestUser(User user) {
        userService.addGoRestUser(user.getId());
        return "user_success";
    }

    @GetMapping(value = "/register")
    public String addAllGoRestUsers() {
        userService.addAllGoRestUsers();
        return "register";
    }

    @GetMapping(value = "/users")
    public String showUsersInDB(Model model) {
        List<User> users = userService.showUsers();
        model.addAttribute("users", users);
        return "users";
    }
}
