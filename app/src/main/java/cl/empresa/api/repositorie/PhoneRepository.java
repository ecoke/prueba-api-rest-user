package cl.empresa.api.repositorie;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.empresa.api.model.PhoneModel;

@Repository
public interface PhoneRepository extends JpaRepository<PhoneModel, Long> {
	abstract List<PhoneModel> findByUserId(UUID userId);
}
