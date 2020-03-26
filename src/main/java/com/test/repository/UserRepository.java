package com.test.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.test.beans.User;

@Repository
public interface UserRepository extends CrudRepository<User,Integer>{
	
	@Query("select u from User u where u.username=:username")
	public User searchByName(@Param("username")String username);

}
