package pl.kk.gorest.registration;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.kk.gorest.user.User;
import pl.kk.gorest.user.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository repo;

    public List<User> showUsers() {
        return repo.findAll();
    }

    public void saveUser(Data request) {
        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .gender(request.getGender())
                .status(request.getStatus())
                .build();
        repo.save(user);

    }

    public User addGoRestUser(Long id) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseGoRest responseGoRest = restTemplate.getForObject("https://gorest.co.in/public/v1/users/{id}", ResponseGoRest.class, id);
        Data data = responseGoRest.getData();
        saveUser(data);
        return User.builder()
                .name(data.getName())
                .email(data.getEmail())
                .gender(data.getGender())
                .status(data.getStatus())
                .build();
    }

    public List<User> addAllGoRestUsers() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseGoRestList responses = restTemplate.getForObject("https://gorest.co.in/public/v1/users", ResponseGoRestList.class);
        List<User> users = responses.getData().stream()
                .map(user -> User.builder()
                        .name(user.getName())
                        .email(user.getEmail())
                        .gender(user.getGender())
                        .status(user.getStatus())
                        .build())
                .collect(Collectors.toList());
        repo.saveAll(users);
        return users;
    }

    public String showHomePage() {
        return "index";
    }
}
