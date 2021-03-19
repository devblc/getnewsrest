package com.bluet.bring.getnewsrest.auth.models;



import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class User {

	//@Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
	
	//@Column(length = 50)
    private String userName;

	//@Column(length = 60)
    private String password;
    private boolean active;
    private String roles;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }
}