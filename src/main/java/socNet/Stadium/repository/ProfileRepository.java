package socNet.Stadium.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import socNet.Stadium.entity.Profile;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {
}
