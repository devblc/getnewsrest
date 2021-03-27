package com.bluet.bring.getnewsrest.auth.repo;



import com.bluet.bring.getnewsrest.auth.models.User;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, Integer> {

	Optional<User> findByUserName(String userName);
	

}