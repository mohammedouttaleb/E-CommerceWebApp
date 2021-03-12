package test.EcommerceWebApp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import test.EcommerceWebApp.dao.UserService;
import test.EcommerceWebApp.model.User;

@Controller
  public class UserController {
   
	/*name of pages and redirections*/
	private final String Regitration_page="register";
	private final String Login_page="login";
	private final String HashMap_error_name="errors";
	//private final String user_error_name="err";
	private final String User_name="user";
	private final String  validationname="validationproduct";
	private final String  homepagename="home";
	/*User data base services and dao model*/
	private UserService userservice;
	 
	

	/*Constructor to avoid to initialiaze the object with the "new " methode --dependency injection--*/
	public UserController(UserService userservice) {
			this.userservice=userservice;
	}
	
	
	
/*les controlleur d'un user qui a fait son inscription*/
	
	@GetMapping("user/{id}")
	public String PageSpecificforUser(@PathVariable("id") int userid ,Model model) {
		User user=userservice.FindUserById(userid);
		//System.out.println("User :"+user);
		try {
			//user.getLogin().toString();
			model.addAttribute(User_name,user);
			return homepagename;
		}catch(NullPointerException e) {
			model.addAttribute("error", e.getMessage());
			return validationname;
		}
		
	}
	
	
	//for calling login page
	@GetMapping("/login")
	public String login() {
		return Login_page;
		}

	//calling registration page
	@GetMapping("registration")
	public String registrationhome() {
		return Regitration_page;
	}
	
	
	//verify new user data input if they are compatible with validation system
	@PostMapping("/verification")
	public String checkInput( User user,@RequestParam(name = "re_password") String repassword,Model model) {
		
		if(userservice.UserCheck(user)) {
			
			if(user.getPassword().equals(repassword)) {
			    userservice.SaveUser(user);
				model.addAttribute(User_name, user);
				return validationname;	
			}
			userservice.getErrors().put("re-passerr", "Retaping Password is Incorect");
			model.addAttribute(HashMap_error_name, userservice.getErrors());
			return Regitration_page;
		}
			
		else {
			model.addAttribute(HashMap_error_name, userservice.getErrors());
			return Regitration_page;
		}
		
		}
	
	
	//verify if the user exists in database and if the login and password are valide
	@PostMapping("/loginverification")
	public String checkUserIds(String login,String password,Model model) {
		
		 try {
		    	User user=userservice.UserExists(login, password);
			
			if(!user.equals(null)) {
				model.addAttribute(User_name, user);
				return validationname;
			}
			
			else {
				model.addAttribute(HashMap_error_name, userservice.getErrors());
				return Login_page;
			}
			
		}catch(NullPointerException e) {
			model.addAttribute(HashMap_error_name, userservice.getErrors());
			return Login_page;
		   }
		}
	  
}
