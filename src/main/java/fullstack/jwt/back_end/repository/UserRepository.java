package fullstack.jwt.back_end.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fullstack.jwt.back_end.entities.User;

public interface UserRepository extends JpaRepository<User, String>{

}
