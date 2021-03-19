package com.bluet.bring.getnewsrest;



import java.util.List;
import java.util.Optional;

import org.springframework.aop.ThrowsAdvice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bluet.bring.getnewsrest.auth.dto.AuthenticationDto;
import com.bluet.bring.getnewsrest.auth.service.UserAuthenticationService;
import com.bluet.bring.getnewsrest.models.News;
import com.bluet.bring.getnewsrest.repo.INewsRepo;

@RestController
@CrossOrigin( origins = "*" )
public class NewsController {
	
	@Autowired
	private INewsRepo repo;	
	
	
	@GetMapping("/")
	public ResponseEntity<String> getNewsTitle() {
		return new ResponseEntity<String>("News Rest is on", HttpStatus.OK);
	}
	
	
    @GetMapping(path = "/get", produces = "application/json")
    public ResponseEntity<?> get() {
        List<?> res;
		try {
			res = repo.findAll();
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
        return ResponseEntity.ok(res);
    }


    @PostMapping("/insert")
    public ResponseEntity<?> insert(@RequestBody News news) {
    	News res;
		try {
			res = repo.insert(news);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
    	return ResponseEntity.ok(res);
    }


    @PutMapping(value = "update/{id}", produces = "application/json")
    public ResponseEntity<?> update(@PathVariable("id") long id, @RequestBody News news) {
    	Optional<News> findIfExist;
    	News res;
		try {
			findIfExist = repo.findById(id);
			// not allow save insert new record
			if (!findIfExist.isPresent()) {
				throw new Exception("News not found to update");
			}
			// set the id in the request to update exact record
			news.setId(findIfExist.get().getId());
			res = repo.save(news);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
    	return ResponseEntity.ok(res);
    }

    /**
     * delete mews
     */
    @DeleteMapping(value = "delete/{id}", produces = "application/json")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
    	Optional<News> res;
		try {
			res = repo.findById(id);
			// get erro if news not exist in db
			if (!res.isPresent()) {
				throw new Exception("News not found to remove");
			}
			repo.delete(res.get());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
		return ResponseEntity.status(HttpStatus.OK).body(res.get());
    }
    
    @GetMapping(value = "/getbyid/{id}", produces = "application/json")
    public ResponseEntity<?> getbyId(@PathVariable("id") Long id) {
    	Optional<News> res;
		try {
			res = repo.findById(id);
			// get erro if news not exist in db
			if (!res.isPresent()) {
				throw new Exception("News not found");
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
		return ResponseEntity.status(HttpStatus.OK).body(res.get());
    }
}
