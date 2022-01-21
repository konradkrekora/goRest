package pl.kk.gorest.user;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public String showUsers(Model model) {
        List<User> usersList = userRepository.findAll();
        model.addAttribute("usersList", usersList);
        return "users";
    }

}
