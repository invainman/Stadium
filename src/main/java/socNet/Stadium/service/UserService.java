package socNet.Stadium.service;

import socNet.Stadium.entity.User;
import socNet.Stadium.jwt.JwtLoginRequest;

import java.util.List;


public interface UserService {

    User create(User user);

    String getTokenForLogin(JwtLoginRequest loginRequest);

    List<User> getAll();

    void deleteByEmail(String email);

    User getByEmail(String email);

    User update(User user);

    User getCurrent();
}