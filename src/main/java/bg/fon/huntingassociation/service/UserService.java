package bg.fon.huntingassociation.service;

import bg.fon.huntingassociation.domain.User;
import bg.fon.huntingassociation.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public User findUser(String email, String password) {
        return this.userRepository.findByEmailAndPassword(email,password);
    }

    public User login(User user) {
        return this.userRepository.findByEmailAndPassword(user.getEmail(), user.getPassword());
    }
}
