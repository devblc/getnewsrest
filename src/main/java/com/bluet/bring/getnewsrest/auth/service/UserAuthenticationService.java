package com.bluet.bring.getnewsrest.auth.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bluet.bring.getnewsrest.auth.JwtUtil;
import com.bluet.bring.getnewsrest.auth.dto.AuthenticationDto;
import com.bluet.bring.getnewsrest.auth.dto.AuthenticationJwtDto;
import com.bluet.bring.getnewsrest.auth.dto.SignUpDto;
import com.bluet.bring.getnewsrest.auth.exceptions.EmailAlreadyExistException;
import com.bluet.bring.getnewsrest.auth.models.User;
import com.bluet.bring.getnewsrest.auth.repo.UserRepository;
import com.bluet.bring.getnewsrest.utils.Utils;


@Service
public class UserAuthenticationService {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	UserDetailsService userDetailService;
	
	//@Autowired
	//SendEmailService sendEmailService;

	@Autowired
	UserRepository userRepository;

	@Autowired
	private JwtUtil jwtTokenUtil;
	
	@Autowired
	private PasswordEncoder passwordEncoder;


	public UserAuthenticationService() {
		super();
	}

	/*
	 * Authenticate the user and get a token string
	 */
	public AuthenticationJwtDto createAuthenticationToken(AuthenticationDto authenticationDto) throws Exception {
		
		try {
			Authentication auth = new UsernamePasswordAuthenticationToken(authenticationDto.getUserName(), authenticationDto.getPassword());
			authenticationManager.authenticate(auth);				
		}
		catch (BadCredentialsException e) {
			throw new Exception("Incorrect username or password", e);
		}
		
		UserDetails userDetails = userDetailService.loadUserByUsername(authenticationDto.getUserName());
		String jwt = jwtTokenUtil.generateToken(userDetails);

		
		return new AuthenticationJwtDto(jwt);
		
	}
	
	/*
	* Register the new user
	*/
	public SignUpDto signUpUser(SignUpDto userRegDto) throws EmailAlreadyExistException {  
	    
		Optional<User> userExist = userRepository.findByUserName(userRegDto.getUserName());
	    
		if (userExist.isPresent()) {
			new EmailAlreadyExistException("Username not allowed: " + userRegDto.getUserName() );
		}
		
		User user = new User();
	    user.setUserName(userRegDto.getUserName());
	    Utils utl = new Utils();
	    user.setId( utl.toHexString(utl.generateSequence(user.getUserName(), "MD5")) ); // transform email into unique id "mongo does not have sequence"
	    user.setPassword(passwordEncoder.encode(userRegDto.getPassword()));
	    user.setRoles("ADMIN");
	    
	    userRepository.save(user);	    
  
	    return (userRegDto);

	}
	
	
	/*
	* Send verification email
	*/
	/*
	public void sendConfirmationMail(String userMail, String codeConfirmation) {

		final SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setTo(userMail);
		mailMessage.setFrom("ceobthk@gmail.com");
		mailMessage.setSubject("Register Confirmation Code");
		mailMessage.setFrom("<MAIL>");

		mailMessage.setText("Thank you for registering. Please access the system and inform the code:"	+ codeConfirmation);

		sendEmailService.sendEmail(mailMessage);
	}
	*/

	/*
	 * Get all users
	 */
	public List<User> getAll() {
		return userRepository.findAll();
	}
	


	

}
