package com.finnplay.springboot.model;




public class CustomUserDetails {
 
    private User user;
     
    public CustomUserDetails(User user) {
        this.user = user;
    }
 

 
  
    public String getPassword() {
        return user.getPassword();
    }
 
   
    public String getUsername() {
        return user.getEmail();
    }

    
     
    public String getFullName() {
        return user.getFirstname() + " " + user.getLastname();
    }
 
}