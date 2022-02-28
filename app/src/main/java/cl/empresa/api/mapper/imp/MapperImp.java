package cl.empresa.api.mapper.imp;

import java.util.ArrayList;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;

import cl.empresa.api.dto.PhoneResponse;
import cl.empresa.api.dto.UserListResponse;
import cl.empresa.api.dto.UserRequest;
import cl.empresa.api.dto.UserResponse;
import cl.empresa.api.mapper.Mapper;
import cl.empresa.api.model.PhoneModel;
import cl.empresa.api.model.UserModel;

public class MapperImp implements Mapper{
	
	private ModelMapper mapper;

	public MapperImp(ModelMapper mapper){
		this.mapper = mapper;
	}
	public ArrayList<UserResponse> toLUserResponse(ArrayList<UserModel> user) {
		ArrayList<UserResponse> usersDto = this.mapper.map(user, new TypeToken<ArrayList<UserResponse>>() {}.getType());
		return usersDto;
	}
	public ArrayList<UserListResponse> toLUserListResponse(ArrayList<UserModel> user) {
		ArrayList<UserListResponse> usersDto = this.mapper.map(user, new TypeToken<ArrayList<UserListResponse>>() {}.getType());
		return usersDto;
	}
	public ArrayList<UserModel> toLUserModel(ArrayList<UserRequest> user) {
		ArrayList<UserModel> userModel = this.mapper.map(user, new TypeToken<ArrayList<UserModel>>() {}.getType());
		return userModel;
	}
	public UserResponse toUserResponse(UserModel user) {
		UserResponse userDto = this.mapper.map(user, UserResponse.class);
		return userDto;
	}
	public UserModel toUserModel(UserRequest user) {
		UserModel userModel = this.mapper.map(user, UserModel.class);
		
		if(userModel.getPhones()!=null){
			for (PhoneModel phone : userModel.getPhones()) {
				phone.setUser(userModel);
			} 
		}
		return userModel;
	}
	@Override
	public ArrayList<PhoneResponse> toLPhoneResponse(ArrayList<PhoneModel> phoneModel) {
		ArrayList<PhoneResponse> usersDto = this.mapper.map(phoneModel, new TypeToken<ArrayList<PhoneResponse>>() {}.getType());
		return usersDto;
	}
	
	
}
