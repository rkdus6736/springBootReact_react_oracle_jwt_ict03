package fullstack.jwt.back_end.config;

import java.util.Base64;
import java.util.Collections;
import java.util.Date;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value; // 주의 (롬복아님)
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication; // 주의 
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier; // 주의 
import com.auth0.jwt.algorithms.Algorithm; // 주의 
import com.auth0.jwt.interfaces.DecodedJWT;

import fullstack.jwt.back_end.dto.UserDTO;
import fullstack.jwt.back_end.service.UserService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor	// lombok이 안되면 변수 final 지우고 매개변수 생성자 만들기~
@Component
public class UserAuthProvider {
	
	// JWT를 생성하고 읽으려면 비밀키가 필요하다.
	// 애플리케이션 yml 파일에서 구성하고 여기에 주입한다. 
	// 그러나 JVM에서 기본값을 가질 수도 있다. 
	
	//import : beans.factory.annotation.Value;
	@Value("${security.jwt.token.secret-key:secret-value}")
	private String secretKey;
	
	private final UserService userService;
//	private UserService userService;
	
//	public UserAuthProvider(UserService userService) {
//		super();
//		this.userService = userService;
//	}

	@PostConstruct
	protected void init() {
		// 일단 텍스트로 된 비밀키를 피하기 위해서 base64로 인코딩
		secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
	}
	
	public String createToken(String id) {
		System.out.println("<<< UserAuthProvider - createToken() >>> ");
		
		Date now = new Date();	// java.util
		Date validty = new Date(now.getTime() + 3600000); 	// 토큰 유효시간 설정 (1시간)
		
		//	JWT를 사용하려면 pom.xml에 java-jwt 추가 
		return JWT.create()
				.withIssuer(id)
				.withIssuedAt(now)
				.withExpiresAt(validty)
				.sign(Algorithm.HMAC256(secretKey));	//import : jwt.algorithms 
	}
	
	public Authentication validationToken(String token) { //import: security.core.Authentication
		System.out.println(" <<< UserAuthProvider - validationToken () >>>");
		System.out.println(" <<< UserAuthProvider - token () >>>" + token);
		
		// import : com.auth0.jwt.JWTVerifier
		JWTVerifier verifier = JWT.require(Algorithm.HMAC256(secretKey)).build();
		
		System.out.println(" <<< UserAuthProvider - validationToken () 1 >>>");
		
		DecodedJWT decoded = verifier.verify(token);	// JWT를 확인하기 위해 먼저 디코딩한다. 유효기간을 초과하면 예외가 발생한다.
		
		System.out.println(" <<< UserAuthProvider - validationToken () 2 >>>");
		UserDTO user = userService.findById(decoded.getIssuer());
		
		// 사용자가 데이터베이스에 존재하는지 확인 
		return new UsernamePasswordAuthenticationToken(user, null, Collections.emptyList());
	}

}
