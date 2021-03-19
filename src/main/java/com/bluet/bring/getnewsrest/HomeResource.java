package com.bluet.bring.getnewsrest;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bluet.bring.getnewsrest.auth.dto.AuthenticationDto;
import com.bluet.bring.getnewsrest.auth.dto.SignUpDto;
import com.bluet.bring.getnewsrest.auth.models.User;
import com.bluet.bring.getnewsrest.auth.service.UserAuthenticationService;



@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost", maxAge = 3600)
public class HomeResource {
	
	@Autowired
	private UserAuthenticationService userAuthenticationService;

	@GetMapping("/")
	public String home() {
		return("<h1>Server is On</h1>");
	}


	@PostMapping("/sign-in")
	public ResponseEntity<?> signIn(@RequestBody AuthenticationDto authenticationDto) {
		
		try {
			return ResponseEntity.ok(userAuthenticationService.createAuthenticationToken(authenticationDto));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
		}
				
	}
	
	@PostMapping(value = "/sign-up")
	public ResponseEntity<?> signUp(@Valid @RequestBody SignUpDto userDto) {

		return ResponseEntity.ok(userAuthenticationService.signUpUser(userDto));

	}

	/*
	@PostMapping(value = "/sendmail")
	public ResponseEntity<?> sendEmail(@Valid @Email @RequestBody EmailConfirmationDto emailConfirmation) {

		userAuthenticationService.sendConfirmationMail(emailConfirmation.getEmail(), "6555");
		return ResponseEntity.ok("mail send");

	}
	*/
	
	@GetMapping(value = "/hello", consumes = "application/json", produces = "application/json")
	public String hello() {

		return "Hello!";

	}


	@GetMapping("/user")
	public String user() {
		return("<h1>Welcome User</h1>");
	}
	

	@GetMapping("/admin")
	public String admin() {
		return("<h1>Welcome Admin</h1>");
	}

	@GetMapping(value = "/get-all", produces = "application/json")
	public ResponseEntity<?> getall(){
    	Optional<List<User>> res;
		try {
			res = Optional.of(userAuthenticationService.getAll());

			if (!res.isPresent()) {
				throw new Exception("Not information found");
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
		return ResponseEntity.status(HttpStatus.OK).body(res.get());
	}

}
