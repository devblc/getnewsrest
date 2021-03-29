package com.bluet.bring.getnewsrest.providers.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.bluet.bring.getnewsrest.providers.models.MoviesProviders;

@Repository
public interface IMoviesProvidersRepo extends MongoRepository<MoviesProviders, Long> {

	 //List<MoviesProviders> findByName(@Param("name") String name);

}
