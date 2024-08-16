package fullstack.jwt.back_end.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor	// 매개변수생성자
@NoArgsConstructor	// 디폴트생성자
@Builder
@Data	// getter,setter
public class CredentialsDTO {

	private String id;
	private char[] password;
	
}
