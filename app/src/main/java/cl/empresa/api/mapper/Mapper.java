package cl.empresa.api.mapper;

import java.util.ArrayList;

import cl.empresa.api.dto.PhoneResponse;
import cl.empresa.api.dto.UserListResponse;
import cl.empresa.api.dto.UserRequest;
import cl.empresa.api.dto.UserResponse;
import cl.empresa.api.model.PhoneModel;
import cl.empresa.api.model.UserModel;

public interface Mapper {
	public ArrayList<UserResponse> toLUserResponse(ArrayList<UserModel> usersModel);
	public ArrayList<UserListResponse> toLUserListResponse(ArrayList<UserModel> usersModel);
	public ArrayList<PhoneResponse> toLPhoneResponse(ArrayList<PhoneModel> phoneModel);
	
	public ArrayList<UserModel> toLUserModel(ArrayList<UserRequest> userDto);
	public UserResponse toUserResponse(UserModel userModel);
	public UserModel toUserModel(UserRequest userDto);
}
