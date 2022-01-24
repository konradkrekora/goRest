package pl.kk.gorest;

import lombok.AllArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.kk.gorest.registration.Data;
import pl.kk.gorest.registration.UserService;
import pl.kk.gorest.user.User;

import java.util.List;

@RestController
@AllArgsConstructor
public class RController {

    private final UserService userService;



    @PostMapping(value = "/register")
    public void register(@RequestBody Data data) {
        userService.saveUser(data);
    }


}


