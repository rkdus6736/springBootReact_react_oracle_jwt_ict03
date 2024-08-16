package fullstack.jwt.back_end.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor	// 매개변수생성자
@NoArgsConstructor	// 디폴트생성자
@Builder
@Data	//  getter,setter
public class UserDTO {
	
	private String id;        
	private String password;  
	private String firstName; //first_name
	private String lastName;  //last_name
	private String token;
	
	
}
