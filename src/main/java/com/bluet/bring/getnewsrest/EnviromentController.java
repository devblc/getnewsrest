package com.bluet.bring.getnewsrest;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bluet.bring.getnewsrest.models.MainMenu;
import com.bluet.bring.getnewsrest.repo.IEnviromentRepo;

@RestController
@CrossOrigin(maxAge = 3600)
@RequestMapping("/env")
public class EnviromentController {

	@Autowired
	private IEnviromentRepo repo;	

    @PostMapping(path = "/add", consumes = "application/json")
    public ResponseEntity<?> Add(@RequestBody MainMenu m) {
    	MainMenu res;
		try {
			res = repo.insert(m);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
    	return ResponseEntity.ok(res);
    }
    
    
    @GetMapping(path = "/hdm/get-all", produces = "application/json")
    public ResponseEntity<?> getAll() {
        List<?> res;
		try {
			res = repo.findAll(Sort.by(Sort.Direction.ASC, "title"));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
        return ResponseEntity.ok(res);
    }

    @PutMapping(value = "update/{id}", produces = "application/json")
    public ResponseEntity<?> update(@PathVariable("id") String id, @RequestBody MainMenu m) {
    	Optional<MainMenu> findIfExist;
    	MainMenu res;
		try {
			findIfExist = repo.findById(id);
			// not allow save insert new record
			if (!findIfExist.isPresent()) {
				throw new Exception("Nothting found to update");
			}
			// set the id in the request to update exact record
			m.setId(findIfExist.get().getId());
			res = repo.save(m);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
    	return ResponseEntity.ok(res);
    }

    @PutMapping(value = "/update-list", produces = "application/json", consumes="application/json")
    public ResponseEntity<?> updateList(@RequestBody List<MainMenu> l) {
    	Optional<MainMenu> findIfExist;
    	List<MainMenu> res;
		try {
			for (MainMenu item : l) {
				findIfExist = repo.findById(item.getId());
				if (findIfExist.isPresent()) {	// not allow insert new record
					item.setId(findIfExist.get().getId()); // // set the id in the request to update exact record
				}
				
			}
			res = repo.saveAll(l); // save all items and return data to rest response			
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
    	return ResponseEntity.ok(res);
    }

}
