package com.bluet.bring.getnewsrest.blogbuilder.repo;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.bluet.bring.getnewsrest.blogbuilder.Profile;

@Repository
public interface IProfileRepo extends MongoRepository<Profile, String>{

	
	
}
