package com.bluet.bring.getnewsrest.repo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import com.bluet.bring.getnewsrest.models.News;

@Repository
public interface INewsRepo extends MongoRepository<News, Long> {

	 List<News> findByTitle(@Param("name") String title);

}
