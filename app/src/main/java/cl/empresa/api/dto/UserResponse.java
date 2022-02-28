package cl.empresa.api.dto;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public class UserResponse {
	private UUID id;
	private String name;
	private String email;
	private String token;
	private Date created;
	private Date modified;
	private Date last_login;
	private Boolean isactive;
	private List<PhoneResponse> phones;
	
	public UserResponse() {
		
	}
 

	public String getToken() {
		return token;
	}

 

	public void setToken(String token) {
		this.token = token;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getModified() {
		return modified;
	}

	public void setModified(Date modified) {
		this.modified = modified;
	}

	public Date getLast_login() {
		return last_login;
	}

	public void setLast_login(Date last_login) {
		this.last_login = last_login;
	}

	public Boolean getIsactive() {
		return isactive;
	}

	public void setIsactive(Boolean isactive) {
		this.isactive = isactive;
	}

 
	public void setPhones(List<PhoneResponse> phones) {
		this.phones = phones;
	}

	public List<PhoneResponse> getPhones() {
		return phones;
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

}
