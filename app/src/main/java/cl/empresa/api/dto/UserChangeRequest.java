package cl.empresa.api.dto;

import java.util.List;
 
 
public class UserChangeRequest {
	private String id;
    private String name;
    private String email;
    private String password;
    private String isactive;
    private List<PhoneRequest> phones;

    
	public String getIsactive() {
		return isactive;
	}

	public void setIsactive(String isactive) {
		this.isactive = isactive;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<PhoneRequest> getPhones() {
		return phones;
	}

	public void setPhones(List<PhoneRequest> phones) {
		this.phones = phones;
	}

	public String getName() {
        return name;
    }
 
    public void setName(String name) {
        this.name = name;
    }
 
    public String getEmail() {
        return email;
    }
 
    public void setEmail(String email) {
        this.email = email;
    }
 
    public String getPassword() {
        return password;
    }
 
    public void setPassword(String password) {
        this.password = password;
    }
    
}
