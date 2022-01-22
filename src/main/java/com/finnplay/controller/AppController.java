package com.finnplay.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.finnplay.springboot.model.User;
import com.finnplay.springboot.repository.UserCustomRepository;
import com.finnplay.springboot.repository.UserRepository;
 
@Controller
public class AppController {
 
    @Autowired
    private UserRepository userRepo;
    
    private UserCustomRepository userCustomRepository;
     
    @GetMapping("/")
    public String viewHomePage() {
        return "index";
    }
    
    @GetMapping("/logout")
    public String logout() {
        return "index";
    }
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
         
        return "sign_up";
    }
    @GetMapping("/login")
    public String showSignInForm(Model model) {
  
        return "sign_in";
    }
    @PostMapping("/process_register")
    public String processRegister(User user) {
       
    	   User user1 = new User();
           user1.setEmail(user.getEmail());
           user1.setBirthday(user.getBirthday());
           user1.setPassword(user.getPassword());
           user1.setUsername(user.getUsername());
           user1.setFirstname(user.getFirstname());
           user1.setLastname(user.getLastname());
        
            userRepo.save(user1);
         
        return "register_success";
    }
    
    @PostMapping("/process_signin")
    public String processSignin(User user, Model model) {
      
    	 
    List<User> users = userRepo.findAll();	 
     String username=    user.getUsername();
     String pass=    user.getPassword();

     String url = "sign_in";
     

     for (int i = 0; i < users.size(); i++) {
		User other = users.get(i);
		if(other.getUsername().equals(username) && other.getPassword().equals(pass))

		{
			other.setIslogged(true);
			 model.addAttribute("user", other);
		url=	"user";
		}
			
		
	}
    
     return url;
      
   
    }

    @PostMapping("/update/{id}")
    public String updateUser(@PathVariable("id") long id,  User user, 
      BindingResult result, Model model) {
        if (result.hasErrors()) {
            user.setId(id);
            return "user";
        }
            
        userRepo.save(user);
        return "user";
    }
}
