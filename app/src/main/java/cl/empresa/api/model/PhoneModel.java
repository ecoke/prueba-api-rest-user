package cl.empresa.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "phono")
public class PhoneModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long phone_id;
    @Column(name = "number")
    private int number;
    @Column(name = "citycode")
    private int citycode;
    @Column(name = "contrycode")
    private int contrycode;
    
    @JoinColumn(name = "user_id", nullable = false, updatable = false)
    @ManyToOne //(optional = true, fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    private UserModel user;

	public long getPhone_id() {
		return phone_id;
	}

	public void setPhone_id(long phone_id) {
		this.phone_id = phone_id;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int getCitycode() {
		return citycode;
	}

	public void setCitycode(int citycode) {
		this.citycode = citycode;
	}

	public int getContrycode() {
		return contrycode;
	}

	public void setContrycode(int contrycode) {
		this.contrycode = contrycode;
	}

	public UserModel getUser() {
		return user;
	}

	public void setUser(UserModel user) {
		this.user = user;
	}
    

}
