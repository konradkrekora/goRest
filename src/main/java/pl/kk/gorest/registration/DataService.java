package pl.kk.gorest.registration;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.kk.gorest.user.User;
import pl.kk.gorest.user.UserRepository;

@Service
@AllArgsConstructor
public class DataService {

    private final UserRepository repo;

    public User register(Data request) {
        User user = new User(request.name, request.email, request.gender, request.status);
        return repo.save(user);

    }

}
