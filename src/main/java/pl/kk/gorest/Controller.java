package pl.kk.gorest;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import pl.kk.gorest.registration.Data;
import pl.kk.gorest.registration.DataService;
import pl.kk.gorest.user.User;

import java.util.Arrays;
import java.util.List;

@RestController
@AllArgsConstructor
public class Controller {

    private final DataService dataService;

    @GetMapping("/register/{id}")
    public User addGoRestUser(@PathVariable("id") String id) {
        RestTemplate restTemplate = new RestTemplate();
        Data data = restTemplate.getForObject("https://gorest.co.in/public/v1/users/" + id, Data.class);
        return dataService.register(data);
    }

    @GetMapping("/register")
    public List<User> showGoRestUsers() {
        RestTemplate restTemplate = new RestTemplate();
        User[] users = restTemplate.getForObject("https://gorest.co.in/public/v1/users", User[].class);
        return Arrays.asList(users);
    }

    @PostMapping("/register")
    public User register(@RequestBody Data request) {
        return dataService.register(request);
    }


}


