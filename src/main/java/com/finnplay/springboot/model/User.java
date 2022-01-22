package com.finnplay.springboot.model;


import javax.persistence.*;

@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
   
    @Column(name="firstname",nullable = false, unique = true, length = 45)
    private String firstname;
 
    @Column(name="lastname",nullable = false, unique = true, length = 45)
    private String lastname;
    
    @Column(name="birthday")
    private String birthday;
 
    private boolean islogged;

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	@Column(name="email",nullable = false, unique = true, length = 45)
    private String email;
 
    @Column(name="username",nullable = false, unique = true, length = 45)
    private String username;
  
    @Column(name="password",nullable = false, unique = true, length = 45)
    private String password;


    @Transient
    private String passwordConfirm;

 
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }
    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
    public boolean authenticate(String username,String password) {
    	if(username == this.username && password == this.password)
    	{
    		return true;
    	}else return false;
    }

	public boolean isIslogged() {
		return islogged;
	}

	public void setIslogged(boolean islogged) {
		this.islogged = islogged;
	}
  
}