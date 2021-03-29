package com.bluet.bring.getnewsrest.blogbuilder;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bluet.bring.getnewsrest.blogbuilder.repo.IProfileRepo;

@Service
public class ProfileService {

	@Autowired
	private IProfileRepo repo;

	public Optional<Profile> getProfile(String id) {
		
		Optional<Profile> res = repo.findById(id);		
		return res;
		
	}
	
	public Optional<Profile> save(Profile profile){
		
		//Optional<Profile> foundProfile = 
		return Optional.of(repo.save(profile));
	}
	
	
//  private Optional<?> findLastProfile(String id){
//	Optional<Profile> p = repo.findById(id);
//	return p;
//  }

}
