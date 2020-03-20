package com.test.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.test.beans.Product;

/**
 * ProductRepository is an interface that extends CrudeRepository
 * which takes in the Product Bean and an Integer as the Primary Key
 *  
 * @Query is a custom query using the "LIKE" clause 
 * @Param is the value we expect to recieve and is mapped with 
 * the String variable name
 *
 */
@Repository
public interface ProductRepository extends CrudRepository<Product, Integer> {
	
	@Query("select p from Product p where p.name like %:name%")
	public List<Product> searchByName(@Param("name") String name);

}
