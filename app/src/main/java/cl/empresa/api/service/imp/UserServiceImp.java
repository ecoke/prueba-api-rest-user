package cl.empresa.api.service.imp;

import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.empresa.api.mapper.Mapper;
import cl.empresa.api.model.UserModel;
import cl.empresa.api.repositorie.UserRepository;
import cl.empresa.api.service.UserService;

@Service
public class UserServiceImp implements UserService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	Mapper mapper;

	@Override
	public UserModel add(UserModel user) {
		user.setCreated(new Date());
		user.setIsactive(true);
		user.setLast_login(null);
		user.setModified(user.getCreated());
		user.setToken(null);
		return userRepository.save(user);
	}

	@Override
	public UserModel change(UserModel user) {
		user.setModified(new Date());
		return userRepository.save(user);
	}
	@Override
	public UserModel changeLogin(UserModel user, String token) {
		user.setLast_login(new Date());
		user.setToken(token);
		return userRepository.save(user);
	}


	@Override
	public ArrayList<UserModel> findAll() {
		ArrayList<UserModel> users;
		users = (ArrayList<UserModel>) userRepository.findAll();
		return users;
	}

	@Override
	public UserModel findByName(String name) {
		Optional<UserModel> userModelOptional;
		userModelOptional = userRepository.findByName(name);
		try{
			return userModelOptional.get();
		}catch(Exception e){
			return null;
		}
	}

	@Override
	public UserModel findByEmail(String email) {
		Optional<UserModel> userModelOptional;
		userModelOptional = userRepository.findByEmail(email);
		try{
			return userModelOptional.get();
		}catch(Exception e){
			return null;
		}

	}

	@Override
	public UserModel findByEmailAndPassword(String email, String password) {
		Optional<UserModel> userModelOptional;
		userModelOptional = userRepository.findByEmailAndPassword(email, password);
		try{
			return userModelOptional.get();
		}catch(Exception e){
			return null;
		}

	}

	@Override
	public UserModel findById(UUID userId) {
		Optional<UserModel> userModelOptional;
		userModelOptional = userRepository.findById(userId);
		try{
			return userModelOptional.get();
		}catch(Exception e){
			return null;
		}

	}

	
	@Override
	public Boolean existsByName(String name) {
		return userRepository.existsByName(name);
	}

	@Override
	public Boolean existsByEmail(String email) {
		return userRepository.existsByEmail(email);
	}

	@Override
	public boolean delete(UUID id) {
		try {
			userRepository.deleteById(id);
			return true;
		}catch (Exception e) {
			return false;
		}
	}

}
