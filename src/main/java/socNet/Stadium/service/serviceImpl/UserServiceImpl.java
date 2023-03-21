package socNet.Stadium.service.serviceImpl;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import socNet.Stadium.entity.User;
import socNet.Stadium.exception.EntityAlreadyExistsException;
import socNet.Stadium.exception.EntityNotFoundException;
import socNet.Stadium.exception.UnauthorizedException;
import socNet.Stadium.jwt.JwtLoginRequest;
import socNet.Stadium.jwt.JwtService;
import socNet.Stadium.repository.UserRepository;
import socNet.Stadium.service.UserService;
import socNet.Stadium.util.AuthUtils;

import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    @Override
    @Transactional
    public User create(User user) {

        userRepository.findByEmail(user.getEmail()).ifPresent(theUser ->
        {
            throw new EntityAlreadyExistsException(String.format("A user with email %s already exists", user.getEmail()));
        });

        user.setRole("USER");
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    @Transactional
    public void deleteByEmail(String email) {
        userRepository.deleteByEmail(email);
    }

    @Override
    public User getByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException(String.format("A user with email %s not found", email)));
    }

    @Override
    public User update(User user) {
        user.setRole(user.getRole());
        return userRepository.save(user);
    }

    @Override
    public User getCurrent() {
        Long id = AuthUtils.getAccountDetails().getUserId();
        return userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(String.format("User with id %s not found", id)));
    }

    public String getTokenForLogin(JwtLoginRequest loginRequest) {
        User theUser = getByEmail(loginRequest.getEmail());
        if (passwordEncoder.matches(loginRequest.getPassword(), theUser.getPassword()))
            return jwtService.generateToken(loginRequest.getEmail());
        throw new UnauthorizedException("Password entered incorrectly");
    }

}