package com.bluet.bring.getnewsrest.auth.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.bluet.bring.getnewsrest.constraints.FieldMatch;



@FieldMatch(first = "password", second = "matchingPassword", message = "The password fields must match")
public class SignUpDto {	


	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@NotNull
    @NotEmpty(message = "User name may not empty")
    @Email(message = "Must be valid email address")
    private String userName;
    
    
    @NotNull
    @NotEmpty(message = "Password name may not empty")
    private String password;

    
    @NotNull
    @NotEmpty(message = "Matching Password name may not empty")
    private String matchingPassword;
    
    public SignUpDto() {
   
    }
    
    

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMatchingPassword() {
		return matchingPassword;
	}

	public void setMatchingPassword(String matchingPassword) {
		this.matchingPassword = matchingPassword;
	}


}
