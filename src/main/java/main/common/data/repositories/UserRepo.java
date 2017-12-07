package main.common.data.repositories;

import main.common.data.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepo extends CrudRepository<User, Long> {

	public List<User> findAll();

	public List<User> findAllByAuthoritiesContains(GrantedAuthority authority);

	public User findByUsername(String username);
}
