package fullstack.jwt.back_end.config;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PasswordConfig {
	
	// 암호화에 대한 인코딩 알고리즘 선택
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
