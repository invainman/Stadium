package socNet.Stadium.service;

import socNet.Stadium.entity.Profile;
import socNet.Stadium.entity.User;

import java.util.List;

public interface ProfileService {

    List<Profile> getAll();

    Profile create(Profile profile, User user);

    Profile update(Profile profile, User user);

    Profile getCurrent(User user);
}
