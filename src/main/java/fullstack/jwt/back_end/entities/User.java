package fullstack.jwt.back_end.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="react_login_tbl")	 // import : javax.persistence
public class User {
	
	@Id
	@Column(name="id")
	private String id;        
	private String password; 
	
	@Column(name="first_name")
	private String firstName; //first_name
	
	@Column(name="last_name")
	private String lastName;  //last_name
	private String token;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}     
	
	
	
}

