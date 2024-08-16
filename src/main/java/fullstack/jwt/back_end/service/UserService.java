package fullstack.jwt.back_end.service;

import java.nio.CharBuffer; // 주의
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import fullstack.jwt.back_end.dto.CredentialsDTO;
import fullstack.jwt.back_end.dto.SignUpDTO;
import fullstack.jwt.back_end.dto.UserDTO;
import fullstack.jwt.back_end.entities.User;
import fullstack.jwt.back_end.exception.AppException;
import fullstack.jwt.back_end.mappers.UserMapper;
import fullstack.jwt.back_end.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor	// lombok이 안되면 변수 final 지우고 매개변수 생성자 만들기~
@Service
public class UserService {

	private final UserRepository userRepository;
	private final UserMapper userMapper;
	private final PasswordEncoder passwordEncoder;
	
	public UserDTO findById(String id) {
		System.out.println("<<< UserService - findById() >>>");
		
		User user = userRepository.findById(id)
				.orElseThrow(() -> new AppException("UnKnown user", HttpStatus.NOT_FOUND));
		return userMapper.toUserDTO(user);
	}
	
	public User login(CredentialsDTO credentialsDTO) {
		System.out.println(" <<< UserService - login() >>> ");
		
		User user = userRepository.findById(credentialsDTO.getId())
				.orElseThrow(() -> new AppException("UnKnown user", HttpStatus.NOT_FOUND));
		
		// import java.nio.CharBuffer;
		if(passwordEncoder.matches(CharBuffer.wrap(credentialsDTO.getPassword()), user.getPassword())) {
			return user;
		}
		throw new AppException("Invalid password", HttpStatus.BAD_REQUEST);
	}
	
	public User register(SignUpDTO userDTO) {
		System.out.println(" <<< UserService - register() >>> ");
		System.out.println(" FirstName : " + userDTO.getFirstName());
		System.out.println(" LastName : " + userDTO.getLastName());
		System.out.println(" Id : " + userDTO.getId());
		System.out.println(" Password : " + userDTO.getPassword());
		System.out.println(" Token : " + userDTO.getToken());
		
		// import : util
		Optional<User> optionalUser = userRepository.findById(userDTO.getId());
		
		if(optionalUser.isPresent()) {
			throw new AppException("Login already exists", HttpStatus.BAD_REQUEST);
		}
		
		User user = new User();
		user.setId(userDTO.getId());
		user.setFirstName(userDTO.getFirstName());
		user.setLastName(userDTO.getLastName());
		user.setToken(userDTO.getToken());
		
		user.setPassword(passwordEncoder.encode(CharBuffer.wrap(userDTO.getPassword())));
		
		User saveUser = userRepository.save(user);
		
		return saveUser;
	}
	
}
