package bg.fon.huntingassociation.service;

import bg.fon.huntingassociation.domain.User;
import bg.fon.huntingassociation.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.Assertions;

import javax.xml.bind.ValidationException;


@SpringBootTest
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    void login_shouldPass() {
        User user = new User();
        user.setEmail("test@example.com");
        user.setPassword("password");

        when(userRepository.findByEmailAndPassword(anyString(), anyString())).thenReturn(user);


        Assertions.assertEquals(user, userService.login(user));
    }

    @Test
    void register_shouldPass() throws ValidationException {
        User user = new User();
        user.setEmail("test@example.com");
        user.setPassword("example");
        user.setJmbg("1404988212451");

        when(userRepository.existsByEmail(anyString())).thenReturn(false);
        when(userRepository.existsByJmbg(anyString())).thenReturn(false);
        when(userRepository.save(any(User.class))).thenReturn(user);

        Assertions.assertEquals(user, userService.register(user));
    }

    @Test
    void register_shouldThrowValidationException() {
        User user = new User();
        user.setEmail("admin@admin.com");
        user.setPassword("secret");
        user.setJmbg("1404986454545");


        when(userRepository.existsByEmail(anyString())).thenReturn(true);

        Assertions.assertThrows(ValidationException.class, () -> userService.register(user));
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    void register__shouldThrowValidationException() {
        User user = new User();
        user.setEmail("test@example.com");
        user.setPassword("password");
        user.setJmbg("1404986121212");

        when(userRepository.existsByEmail(anyString())).thenReturn(false);
        when(userRepository.existsByJmbg(anyString())).thenReturn(true);

        Assertions.assertThrows(ValidationException.class, () -> userService.register(user));
        verify(userRepository, never()).save(any(User.class));
    }
}