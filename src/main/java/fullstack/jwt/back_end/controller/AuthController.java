package fullstack.jwt.back_end.controller;

import java.net.URI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import fullstack.jwt.back_end.config.UserAuthProvider;
import fullstack.jwt.back_end.dto.CredentialsDTO;
import fullstack.jwt.back_end.dto.SignUpDTO;
import fullstack.jwt.back_end.entities.User;
import fullstack.jwt.back_end.service.UserService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class AuthController {
	
	private static final Logger logger = LoggerFactory.getLogger(AuthController.class);
	
	private final UserService userService;
	private final UserAuthProvider userAuthprovider;
	
	// http://localhost:8081 -> username:user / password:부트에서 생성한 비밀번호 붙여넣기 
	@GetMapping({"","/"})
	public String index() {
		System.out.println("<<< index >>>");
		
		return "index";	//주소가 아니라 '값'을 브라우저에 출력
	}
	
	@PostMapping("/login")
	public ResponseEntity<User> login(@RequestBody CredentialsDTO credentialsDTO) {
		System.out.println("<<< AuthController - login() ");
		
		User user = userService.login(credentialsDTO);
		
		System.out.println("token : " + userAuthprovider.createToken(user.getId()));
		user.setToken(userAuthprovider.createToken(user.getId()));
		
		return ResponseEntity.ok(user);	// 크롬브라우저 F12 > Headers : 200 OK : 새로운 JWT를 반환 
	}
	
	@PostMapping("/register")
	public ResponseEntity<User> regsiter(@RequestBody SignUpDTO signUpDTO) {
		System.out.println(" <<< AuthController - register() >>> ");
		
		User user = new User();
		
		//토큰 => 크롬 F12 application탭 > local Storage에 보관됨
		signUpDTO.setToken(userAuthprovider.createToken(user.getId())); //추가해야 토큰 가져옴
		user = userService.register(signUpDTO);	// 리액트에서 넘어온 정보 + 토큰 => Insert
		
		return ResponseEntity.created(URI.create("/users/" + user.getId()))
				.body(user);	//크롬 NetWork - Headers : 201 Created 반환
	}
	
	
}
