package com.bluet.bring.getnewsrest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bluet.bring.getnewsrest.extractors.ExtractorsService;

@RestController
@RequestMapping("/scrapers") 
@CrossOrigin(maxAge = 3600)
public class ExtractorProvidersController {


	@Autowired
	ExtractorsService service;
/*
 * Get movies providers information, like: disney, netflix, etc	
 */
@GetMapping(path = "/providers")
public ResponseEntity<?> getProviders(){
	
	
	
	try {
		return ResponseEntity.ok(service.getProvidersInfo());
	}catch (Exception e) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
	}
	

}

}
