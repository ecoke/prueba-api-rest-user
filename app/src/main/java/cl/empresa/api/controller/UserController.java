package cl.empresa.api.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.empresa.api.dto.LoginRequest;
import cl.empresa.api.dto.MessageResponse;
import cl.empresa.api.dto.UserChangeRequest;
import cl.empresa.api.dto.UserListResponse;
import cl.empresa.api.dto.UserRequest;
import cl.empresa.api.dto.UserResponse;
import cl.empresa.api.mapper.Mapper;
import cl.empresa.api.model.PhoneModel;
import cl.empresa.api.model.UserModel;
import cl.empresa.api.service.PhoneService;
import cl.empresa.api.security.jwt.JwtUtils;
import cl.empresa.api.service.UserService;
import cl.empresa.api.util.Validation;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserService userService;

	@Autowired
	PhoneService phoneService;
	
	@Autowired
	Mapper mapper;

	@Autowired
	JwtUtils jwtUtils;

	@PostMapping("/login")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
		UserModel userModel;
	 	userModel = userService.findByEmailAndPassword(loginRequest.getEmail(), loginRequest.getPassword());
		if(userModel == null) { 
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new MessageResponse("Usuario o clave incorrectos"));
		}else {
			String token = jwtUtils.getJwtToken(userModel.getEmail());
			userModel = userService.changeLogin(userModel, token);
		 	List<PhoneModel> phoneModels = phoneService.findByUserId(userModel.getId());
			userModel.setPhones(phoneModels);
			UserResponse userDto = mapper.toUserResponse(userModel);
			return ResponseEntity.ok().body(userDto);
		}
	}
	
	@PostMapping("/registro")
	public ResponseEntity<?> registerUser(@Valid @RequestBody UserRequest userRequest) {
		ResponseEntity<?> responseEntity;
		responseEntity = validateNewUser(userRequest);
		if(responseEntity!=null) {
			return responseEntity;
		}
		String token = jwtUtils.getJwtToken(userRequest.getEmail());
		UserModel  user = mapper.toUserModel(userRequest);
		//user.setPassword(encoder.encode(userRequest.getPassword()));
		user.setPassword(userRequest.getPassword());
		user.setToken(token);
		user= userService.add(user);
		UserResponse userDto = mapper.toUserResponse(user);
		return ResponseEntity.ok().body(userDto);
	}

	@PostMapping("/signout")
	public ResponseEntity<?> logoutUser() {
		return ResponseEntity.ok().body(new MessageResponse("Sesión cerrada con éxito"));
	}
	
 	@GetMapping(path = "/list")
	public ResponseEntity<?> findAllUser(){
 		ArrayList<UserListResponse> userResponses;
 		ArrayList<UserModel> userModels;
 		userModels = userService.findAll();
 		userResponses = mapper.toLUserListResponse(userModels);
 		return ResponseEntity.ok().body(userResponses);
	}
 	
	@PostMapping("/get")
	public ResponseEntity<?> getUser(String id) {
		UserModel userModel;
		ResponseEntity<?> responseEntity;
		responseEntity =validateUserId(id);
		if(responseEntity!=null) return responseEntity;
		
		try {
			UUID uuid = UUID.fromString(id);
			userModel = userService.findById(uuid );
		}catch (IllegalArgumentException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageResponse("UUID no válido"));
		}
	 	
	 	if(userModel == null) { 
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageResponse("Usuario no encontrado"));
		}else {
		 	List<PhoneModel> phoneModels = phoneService.findByUserId(userModel.getId());
			userModel.setPhones(phoneModels);
			UserResponse userDto = mapper.toUserResponse(userModel);
			return ResponseEntity.ok().body(userDto);
		}
		
	}
	
	@PatchMapping("/change")
	public ResponseEntity<?> changeUser(@Valid @RequestBody UserChangeRequest userChangeRequest) {
		UserModel userModel;
		ResponseEntity<?> responseEntity;
		responseEntity =validateUserId(userChangeRequest.getId());
		if(responseEntity!=null) return responseEntity;
		
		try {
			UUID uuid = UUID.fromString(userChangeRequest.getId());
			userModel = userService.findById(uuid );
		}catch (IllegalArgumentException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageResponse("UUID no válido"));
		}
	 	if(userModel == null) { 
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageResponse("Usuario no encontrado"));
		}else {
			responseEntity = validateChangeUser(userModel, userChangeRequest);
			if(responseEntity!=null) {
				return responseEntity;
			}
			userModel = userService.change(userModel);
		 	//List<PhoneModel> phoneModels = phoneService.findByUserId(userModel.getId());
			//userModel.setPhones(phoneModels);
			UserResponse userDto = mapper.toUserResponse(userModel);
			return ResponseEntity.ok().body(userDto);
		}
	}
	
	@DeleteMapping("/delete")
	public ResponseEntity<?> changeUser(String id) {
		ResponseEntity<?> responseEntity;
		responseEntity =validateUserId(id);
		if(responseEntity!=null) return responseEntity;
		try {
			UUID uuid = UUID.fromString(id);
			if(userService.delete(uuid)) {
				return ResponseEntity.ok().body("Usuario eliminado exitosamente");
			}else {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageResponse("Usuario no encontrado"));
			}
		}catch (IllegalArgumentException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageResponse("UUID no válido"));
		}
		
	}
	
	private  ResponseEntity<?> validateUserId(String userId) {
		if(Validation.isStringNullOrEmpty(userId)) {
			return ResponseEntity.badRequest().body(new MessageResponse("Debe proporcionar un id válido"));
		}
		return null;
	}
	private  ResponseEntity<?> validateChangeUser(UserModel userRequestOld, UserChangeRequest userRequestNew) {
 
		if(!Validation.isStringNullOrEmpty(userRequestNew.getName())) {
			userRequestOld.setName(userRequestNew.getName());
		}
		if(!Validation.isStringNullOrEmpty(userRequestNew.getEmail())) {
			userRequestOld.setEmail(userRequestNew.getEmail());
		}
		if(!Validation.isStringNullOrEmpty(userRequestNew.getPassword())) {
			userRequestOld.setPassword(userRequestNew.getPassword());
		}
		if(!Validation.isStringNullOrEmpty(userRequestNew.getIsactive())) {
			userRequestOld.setIsactive(Boolean.parseBoolean(userRequestNew.getIsactive()));
		}
		
		if(!Validation.isEmail(userRequestOld.getEmail())) {
			return ResponseEntity.badRequest().body(new MessageResponse("Formato de correo no válido x@y.z"));
		}

		return null;
	}
	private  ResponseEntity<?> validateNewUser(UserRequest userRequest) {
		if(Validation.isStringNullOrEmpty(userRequest.getName())) {
			return ResponseEntity.badRequest().body(new MessageResponse("Debe proporcionar un nombre de usuario"));
		}
		if(Validation.isStringNullOrEmpty(userRequest.getEmail())) {
			return ResponseEntity.badRequest().body(new MessageResponse("Debe proporcionar un email de usuario"));
		}
		if(Validation.isStringNullOrEmpty(userRequest.getPassword())) {
			return ResponseEntity.badRequest().body(new MessageResponse("Debe proporcionar una calve"));
		}
		
		if(!Validation.isEmail(userRequest.getEmail())) {
			return ResponseEntity.badRequest().body(new MessageResponse("Formato de correo no válido x@y.z"));
		}
		
		if (userService.existsByName(userRequest.getName())) {
			return ResponseEntity.badRequest().body(new MessageResponse("Nombre de suario ya registrado"));
		}

		if (userService.existsByEmail(userRequest.getEmail())) {
			return ResponseEntity.badRequest().body(new MessageResponse("Email ya registrado"));
		}
		 return null;
	}

}
