package cl.empresa.api.service.imp;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.empresa.api.mapper.Mapper;
import cl.empresa.api.model.PhoneModel;
import cl.empresa.api.repositorie.PhoneRepository;
import cl.empresa.api.service.PhoneService;

@Service
public class PhoneServiceImp implements PhoneService{

	@Autowired
	PhoneRepository phoneRepository;
	
	@Autowired
	Mapper mapper;
	
	public List<PhoneModel> findByUserId(UUID userId){
		List<PhoneModel> phoneModels;
		phoneModels = phoneRepository.findByUserId(userId);
		return phoneModels;
	}

}
