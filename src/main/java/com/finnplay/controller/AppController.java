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
import com.finnplay.springboot.repository.UserRepository;
 
@Controller
public class AppController {
 
    @Autowired
    private UserRepository userRepo;
    
   
     
    @GetMapping("/")
    public String viewHomePage() {
        return "index";
    }
    
    @PostMapping("/logout")
    public String logout(User user) {
    	 List<User> users = userRepo.findAll();	
    	for(User u: users)
    	{
    		if(u.isIslogged())
    		{
    			u.setIslogged(false);
    			userRepo.save(u);
    		}
    	}
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
    @GetMapping("/forgetPassword")
    public String showForgetPasswordForm(Model model) {
  
        return "forget_password";
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
    @PostMapping("/process_QuestionAnswer")
    public String AnswerQuestion(User user, Model model)
    {
    	 String url=null;
    	String answer = user.getAnswer();
    	String username = user.getUsername();
    	List<User> users = userRepo.findAll();
    	 users.forEach((userinside) -> {
        if (userinside.getAnswer().equals(answer))
        {
        	processUrl("password");
        	        
        }
         });
    	 return processUrl("sign_in");

    }
    public String processUrl(String url)
    {
    	
		return url;
    	
    }
    @PostMapping("/process_getQuestion")
    public String getQuestion(User user,Model model)
    {
    	 String username=    user.getUsername();
    	  String url = "sign_in";
    	List<User> users = userRepo.findAll();
    	for (int i = 0; i < users.size(); i++) {
    		User other = users.get(i);
    		if(other.getUsername().equals(username))
    		{
    			String question=other.getQuestion();
		        String answer = other.getAnswer();
		      
   			 model.addAttribute("user", other);
    		}
    		    
    		 
    			
    		url=	"questionAnswer";
    			
    		
    			
    		
    	}
		return url;
    	
    }
    @PostMapping("/process_forgetpassword")
    public String forgetPassword(User user,Model model)
    {
    	  String url = "sign_in";
    	  String answersubmitted=    user.getAnswer();
    	  String username = user.getUsername();
    	List<User> users = userRepo.findAll();
    	for (int i = 0; i < users.size(); i++) {
    		User other = users.get(i);
    		
    		    String question=other.getQuestion();
    		    String answer = other.getAnswer();
    			if(answersubmitted.equals(answer))
    			{
    				 model.addAttribute("user", other);
    				 url=	"password";
    			}else {
    				
    				url=	"sign_in";
    			}
    			
    			 
    		
    			
    		
    			
    		
    	}
		return url;
    	
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
			if(!other.isIslogged()) {
			other.setIslogged(true);
			 userRepo.save(other);
			 model.addAttribute("user", other);
		url=	"user";
			}else 
			{
				 model.addAttribute("user", other);
				url ="user";
			}
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
        user.setIslogged(true);
        userRepo.save(user);
        return "user";
    }
}
