package com.test.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.test.beans.User;

@Repository
public interface UserRepository extends CrudRepository<User,Integer>{

}
