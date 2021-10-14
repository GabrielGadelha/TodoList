package br.edu.ufersa.pw.todolist.repositories;

import java.time.LocalDate; 
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RestResource;
import br.edu.ufersa.pw.todolist.entity.Concluded;


public interface ConcludedRepository extends JpaRepository<Concluded,Long>{
	@RestResource(exported = false)
	@Query(
			  value = "select t.id, t.id_user, t.todo, t.completed_in from tb_concluded t INNER JOIN tb_users user "
			  		+ "ON user.id=t.id_user and user.email= :email and t.dead_line= :deadline", 
			  nativeQuery = true)
	List<Concluded> findByUserAndDeadLine(String email, LocalDate deadline);
	@RestResource(exported = false)
	@Query(
			  value = "select t.id, t.id_user, t.todo, t.completed_in from tb_concluded t INNER JOIN tb_users user "
			  		+ "ON user.id=t.id_user and user.email= :email", 
			  nativeQuery = true)
	List<Concluded> findByUser(String email);
}
