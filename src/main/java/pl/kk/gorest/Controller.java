package pl.kk.gorest;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import pl.kk.gorest.registration.*;
import pl.kk.gorest.user.User;
import pl.kk.gorest.user.UserRepository;
import pl.kk.gorest.user.UserService;

import java.util.ArrayList;
import java.util.List;

@RestController
@AllArgsConstructor
public class Controller {

    private final DataService dataService;
    private final UserService userService;
    private final UserRepository userRepository;

    @GetMapping(value = "/register/{id}")
    public void addGoRestUser(@PathVariable("id") String id) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseGoRest responseGoRest = restTemplate.getForObject("https://gorest.co.in/public/v1/users/{id}", ResponseGoRest.class, id);
        Data data = responseGoRest.getData();
        dataService.register(data);
    }

    @GetMapping(value = "/register")
    public List<User> showGoRestUsers() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseGoRestList responses = restTemplate.getForObject("https://gorest.co.in/public/v1/users", ResponseGoRestList.class);
        List<User> userList = new ArrayList<>();
        for (Data data: responses.getDataList()) {
            userList.add(new User(data.getName(), data.getEmail(), data.getGender(), data.getStatus()));
        }
        userRepository.saveAll(userList);
        return userList;
    }

    @GetMapping(value = "/users")
    public List<User> showUsersInDB(){
        return userService.showUsers();
    }

    @PostMapping(value = "/register")
    public User register(@RequestBody Data data) {
        return dataService.register(data);
    }


}


