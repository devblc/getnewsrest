package com.bluet.bring.getnewsrest.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bluet.bring.getnewsrest.models.News;

@Repository
public interface INewsRepo extends MongoRepository<News, Long> {

	 List<News> findByTitle(@Param("name") String title);
	 Optional<News> findById(@Param("name") String id);

}
