package com.bluet.bring.getnewsrest;



import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

import org.springframework.aop.ThrowsAdvice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bluet.bring.getnewsrest.auth.dto.AuthenticationDto;
import com.bluet.bring.getnewsrest.auth.service.UserAuthenticationService;
import com.bluet.bring.getnewsrest.models.News;
import com.bluet.bring.getnewsrest.providers.models.MoviesProviders;
import com.bluet.bring.getnewsrest.repo.INewsRepo;

@RestController
@CrossOrigin(maxAge = 3600)
@RequestMapping("/news")
public class NewsController {
	
	@Autowired
	private INewsRepo repo;	
	
	
	@GetMapping("/")
	public ResponseEntity<String> getNewsTitle() {
		return new ResponseEntity<String>("News Rest Web is on", HttpStatus.OK);
	}
	
	
    @GetMapping(path = "/get-all", produces = "application/json")
    public ResponseEntity<?> getAll() {
        List<?> res;
		try {
			res = repo.findAll(Sort.by(Sort.Direction.ASC, "publishedAt"));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
        return ResponseEntity.ok(res);
    }


    @PostMapping(path = "/add", consumes = "application/json")
    public ResponseEntity<?> Add(@RequestBody News news) {
    	News res;
		try {
			res = repo.insert(news);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
    	return ResponseEntity.ok(res);
    }


    @PutMapping(value = "update/{id}", produces = "application/json")
    public ResponseEntity<?> update(@PathVariable("id") String id, @RequestBody News news) {
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
    public ResponseEntity<?> delete(@PathVariable("id") String id) {
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
    
    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<?> getbyId(@PathVariable("id") String id) {
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


    @PostMapping(path = "/add-list", consumes = "application/json")
    public ResponseEntity<?> AddList(@RequestBody List<News> items) {
    	List<News> res;
		try {
			items.forEach(item -> {				
				item.setId( toHexString(generateSequence(item.getTitle())) );  //gen256 a id
			});
			res = repo.insert(items);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
    	return ResponseEntity.ok(res);
    }

   
    private byte[] generateSequence(String seq) {
    	MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e) {
			System.out.print(e.getMessage());
		} 
    	return md.digest(seq.getBytes(StandardCharsets.UTF_8)); 
    }
    
    // Convert byte array into signum representation  
    public String toHexString(byte[] hash) 
    { 
        BigInteger number = new BigInteger(1, hash);    
        StringBuilder hexString = new StringBuilder(number.toString(16));
        
        return hexString.toString();  
   } 

    
}
