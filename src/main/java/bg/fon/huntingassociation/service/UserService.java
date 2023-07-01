package bg.fon.huntingassociation.service;

import bg.fon.huntingassociation.domain.User;
import bg.fon.huntingassociation.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.ValidationException;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public User login(User user) {
        return userRepository.findByEmailAndPassword(user.getEmail(), user.getPassword());
    }

    public User register(User user) throws ValidationException {
        if(userRepository.existsByEmail(user.getEmail()))
            throw new ValidationException("Email already in use!");
        if(userRepository.existsByJmbg(user.getJmbg()))
            throw  new ValidationException("User with this JMBG already have an account. Please, contact system administrators.");
        return  userRepository.save(user);
    }
}
