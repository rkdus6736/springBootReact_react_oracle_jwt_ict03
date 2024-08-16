package fullstack.jwt.back_end.mappers;

import org.apache.ibatis.annotations.Mapper;

import fullstack.jwt.back_end.dto.UserDTO;
import fullstack.jwt.back_end.entities.User;

public interface UserMapper {

	UserDTO toUserDTO(User user);
}
