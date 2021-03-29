package com.bluet.bring.getnewsrest.providers.services;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;

import com.bluet.bring.getnewsrest.providers.models.MoviesProviders;
import com.bluet.bring.getnewsrest.providers.repo.IMoviesProvidersRepo;

public class MoviesProviderService {
	
	@Autowired
	IMoviesProvidersRepo repo; 
	
	public Optional<?> addList(List<MoviesProviders> m) throws Exception {  
	    
		m.forEach( p->
					p.setId(new Random().nextLong())
				);
		    
	    repo.saveAll(m);	    
  
	    return Optional.ofNullable((m));

	}

	public Optional<?> listAll() throws Exception {  
	    		    
		List<MoviesProviders> mlist = repo.findAll();	    
	    return Optional.ofNullable(mlist);

	}

}
