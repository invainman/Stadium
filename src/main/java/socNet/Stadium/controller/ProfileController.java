package socNet.Stadium.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import socNet.Stadium.entity.Profile;
import socNet.Stadium.service.ProfileService;
import socNet.Stadium.service.UserService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/profiles")
public class ProfileController {

    private final ProfileService profileService;

    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<Profile>> getAll() {
        return new ResponseEntity<>(profileService.getAll(), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Profile> create(@RequestBody Profile profile) {
        return new ResponseEntity<>(profileService.create(profile, userService.getCurrent()), HttpStatus.OK);
    }

    @PutMapping()
    public ResponseEntity<Profile> update(@RequestBody Profile profile) {
        return new ResponseEntity<>(profileService.update(profile, userService.getCurrent()), HttpStatus.OK);
    }

    @GetMapping("/current")
    public ResponseEntity<Profile> getCurrent() {
        return new ResponseEntity<>(profileService.getCurrent(userService.getCurrent()), HttpStatus.OK);
    }
}
