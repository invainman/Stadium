package socNet.Stadium.service.serviceImpl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import socNet.Stadium.entity.Profile;
import socNet.Stadium.entity.User;
import socNet.Stadium.exception.EntityAlreadyExistsException;
import socNet.Stadium.repository.ProfileRepository;
import socNet.Stadium.repository.UserRepository;
import socNet.Stadium.service.ProfileService;

import java.util.List;

@Service
@AllArgsConstructor
public class ProfileServiceImpl implements ProfileService {

    private final ProfileRepository profileRepository;

    private final UserRepository userRepository;

    @Override
    public List<Profile> getAll() {
        return profileRepository.findAll();
    }

    @Override
    public Profile create(Profile profile, User user) {
        if (user.getProfile() != null)
            throw new EntityAlreadyExistsException("Profile already exist");
        profileRepository.save(profile);
        user.setProfile(profile);
        userRepository.save(user);
        return user.getProfile();
    }

    @Override
    public Profile update(Profile profile, User user) {
        Profile currentProfile = getCurrent(user);
        currentProfile.setFirstname(profile.getFirstname());
        currentProfile.setLastname(profile.getLastname());
        currentProfile.setAboutMe(profile.getAboutMe());
        return profileRepository.save(currentProfile);
    }

    @Override
    public Profile getCurrent(User user) {
        return user.getProfile();
    }
}
