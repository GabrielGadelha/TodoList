package br.edu.ufersa.pw20222todoList.domain.repository;
import br.edu.ufersa.pw20222todoList.domain.entity.User;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long>{

	User findByUuid(UUID id);
	User findByEmail(String email);
	User findByEmailAndSenha(String email, String senha);

}
