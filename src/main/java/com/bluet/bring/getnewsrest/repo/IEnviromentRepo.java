package com.bluet.bring.getnewsrest.repo;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bluet.bring.getnewsrest.models.MainMenu;

@Repository
public interface IEnviromentRepo extends MongoRepository<MainMenu, String> {

	 List<MainMenu> findByTitle(@Param("title") String title);

}


