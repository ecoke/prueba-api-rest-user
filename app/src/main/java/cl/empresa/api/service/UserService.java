package cl.empresa.api.service;

import java.util.ArrayList;
import java.util.UUID;

import cl.empresa.api.model.UserModel;

public interface UserService {
	public ArrayList<UserModel> findAll();
	public UserModel add(UserModel user);
	public boolean delete(UUID id);
	public UserModel findById(UUID id);
	public UserModel change(UserModel user);
	public UserModel changeLogin(UserModel user, String token);
	public UserModel findByName(String name);
	public UserModel findByEmail(String email);
	public UserModel findByEmailAndPassword(String email, String password);
	public Boolean existsByName(String name);
	public Boolean existsByEmail(String email);
}
