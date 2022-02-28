package cl.empresa.api.dto;

import java.util.List;
import java.util.UUID;

public class UserloginResponse {
	private UUID id;
	private String name;
	private String email;
	private String token;
	private List<String> roles;
	
	public UserloginResponse(UUID id, String name, String email, String token, List<String> roles) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.roles = roles;
		this.token=token;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	
	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getRoles() {
		return roles;
	}
}
