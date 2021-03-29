package com.bluet.bring.getnewsrest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bluet.bring.getnewsrest.providers.models.MoviesProviders;
import com.bluet.bring.getnewsrest.providers.repo.IMoviesProvidersRepo;

@RestController
@CrossOrigin(maxAge = 3600)
@RequestMapping("/movies")
public class MoviesController {

	@Autowired
	private IMoviesProvidersRepo repo;	

	@GetMapping(path = "/", produces = "application/json")
    public ResponseEntity<?> getAllProviders() {
        List<?> res;
		try {
			res = repo.findAll(Sort.by(Sort.Direction.ASC, "name"));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
        return ResponseEntity.ok(res);
    }


	@PostMapping(path = "/", produces = "application/json")
    public ResponseEntity<?> addListProviders( 
    		@RequestBody List<MoviesProviders> moviesproviders) {
        List<?> res;
		try {
			res = repo.saveAll(moviesproviders);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
        return ResponseEntity.ok(res);
    }

}
