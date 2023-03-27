package socNet.Stadium.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import socNet.Stadium.entity.User;
import socNet.Stadium.jwt.JwtLoginRequest;
import socNet.Stadium.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> getAll() {
        return new ResponseEntity<>(userService.getAll(), HttpStatus.OK);
    }

    @PostMapping("/registration")
    public ResponseEntity<User> create(@RequestBody User user) {
        return new ResponseEntity<>(userService.create(user), HttpStatus.CREATED);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<String> getTokenForLogin(@RequestBody JwtLoginRequest loginRequest) {
        return new ResponseEntity<>(userService.getTokenForLogin(loginRequest), HttpStatus.OK);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<User> getByEmail(@PathVariable String email) {
        return new ResponseEntity<>(userService.getByEmail(email), HttpStatus.OK);
    }

    @DeleteMapping("/email/{email}")
    public ResponseEntity<Void> deleteByEmail(@PathVariable String email) {
        userService.deleteByEmail(email);
        return ResponseEntity.noContent().build();
    }

    @PutMapping()
    public ResponseEntity<User> update(@RequestBody User user) {
        return new ResponseEntity<>(userService.update(user), HttpStatus.OK);
    }

    @GetMapping("/current")
    public ResponseEntity<User> getCurrent() {
        return new ResponseEntity<>(userService.getCurrent(), HttpStatus.OK);
    }
}
