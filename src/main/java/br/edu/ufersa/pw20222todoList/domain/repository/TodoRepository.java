package br.edu.ufersa.pw20222todoList.domain.repository;

import java.time.LocalDate;  
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.format.annotation.DateTimeFormat;

import br.edu.ufersa.pw20222todoList.domain.entity.Todo;


@RepositoryRestResource
public interface TodoRepository extends JpaRepository<Todo,Long>{
	List<Todo> findByDeadLine(@Param("deadline") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate deadline);
	@RestResource(exported = false)
	@Query(
			  value = "select t.id, t.id_user, t.todo, t.dead_line from tb_todo t INNER JOIN tb_users user "
			  		+ "ON user.id=t.id_user and user.email= :email and t.dead_line= :deadline", 
			  nativeQuery = true)
	List<Todo> findByUserAndDeadLine(String email, LocalDate deadline);
	@RestResource(exported = false)
	@Query(
			  value = "select t.id, t.id_user, t.todo, t.dead_line from tb_todo t INNER JOIN tb_users user "
			  		+ "ON user.id=t.id_user and user.email= :email", 
			  nativeQuery = true)
	List<Todo> findByUser(String email);
}
