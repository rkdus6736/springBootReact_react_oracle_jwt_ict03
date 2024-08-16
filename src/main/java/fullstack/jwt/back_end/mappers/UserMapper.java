package fullstack.jwt.back_end.mappers;

import org.apache.ibatis.annotations.Mapper;

import fullstack.jwt.back_end.dto.SignUpDTO;
import fullstack.jwt.back_end.dto.UserDTO;
import fullstack.jwt.back_end.entities.User;

@Mapper
public interface UserMapper {

	UserDTO toUserDTO(User user);
	User signUpToUser(SignUpDTO userDTO);
}
