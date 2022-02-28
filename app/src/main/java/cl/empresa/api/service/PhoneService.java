package cl.empresa.api.service;

import java.util.List;
import java.util.UUID;

import cl.empresa.api.model.PhoneModel;

public interface PhoneService {
	public List<PhoneModel> findByUserId(UUID userId);
}
