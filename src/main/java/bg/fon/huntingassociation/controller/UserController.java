package bg.fon.huntingassociation.controller;

import bg.fon.huntingassociation.domain.User;
import bg.fon.huntingassociation.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/find/{email}/{password}")
    public ResponseEntity<?> findUser(@PathVariable("email") String email,
            @PathVariable("password") String password) {
        try {
            return new ResponseEntity<>(userService.findUser(email,password), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> logIn(@RequestBody User user) {
        try {
            return new ResponseEntity<>(userService.login(user), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
