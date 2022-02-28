package cl.empresa.api.dto;


public class PhoneRequest {
	private long phone_id;
    private int number;
    private int citycode;
    private int contrycode;
    
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
    
}
