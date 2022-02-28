package cl.empresa.api.repositorie;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.empresa.api.model.UserModel;

@Repository
public interface UserRepository extends JpaRepository<UserModel, UUID> {
	  Optional<UserModel> findByName(String name);
	  Optional<UserModel> findByEmail(String email);
	  abstract Optional<UserModel> findByEmailAndPassword(String email, String password);
	  Boolean existsByName(String name);
	  Boolean existsByEmail(String email);

}
