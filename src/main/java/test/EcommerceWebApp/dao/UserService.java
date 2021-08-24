package test.EcommerceWebApp.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import test.EcommerceWebApp.model.User;
import test.EcommerceWebApp.repositories.Userrepository;


@Component
public class UserService  {
	

	
	 private  Userrepository userrepo;
	
	 
	 
	 private Map<String,String> errors=new HashMap<>();
	
	 
	
	
	
	public UserService(Userrepository userrepo) {
		
		this.userrepo = userrepo;
		
		
		
	}


	
	
	public User FindUserById(int id) {
		
		return userrepo.findById(id).orElse(new User());
	}
	
	public User SaveUser(User user) {
		
		return userrepo.save(user);
	}
	
	
	
	
	
	
	
	
	
	public Map<String, String> getErrors() {
		return errors;
	}


	public void setErrors(Map<String, String> errors) {
		this.errors = errors;
	}





















  
	
	public boolean  UserCheck(User user) {
		
		if(user.equals(null)) {
			return false;
		}
			
		else {
			boolean statut=true;
			if(!this.getErrors().isEmpty())
				this.getErrors().clear();
		  
			if(!EmailChecked(user.getEmail())) {
				System.out.println("madkhell1");
				statut=false;
				
			}
			 if(!LoginChecked(user.getLogin())) {
				statut=false;
				
			}
			 if(!PasswordChecked(user.getPassword())) {
				statut=false;
			}

			 
			return statut;}
		}
	
	
	/*methode pour checker lelogin est valide ou nons*/
	public User UserExists(String login,String Password) {
		
		List <User> userlist=userrepo.findAll();
		if(!userlist.equals(null)) {
		
			for (User user : userlist) {
				   if(user.getLogin().equals(login)&&user.getPassword().equals(Password)) {
					   
					   return user;
				   }
				}
				errors.put("loginerror", "Login or Password Invalide");
				return null;
		}
		errors.put("loginerror", "Login or Password Invalide");
		 return null;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	private boolean EmailChecked(String email) {
		System.out.println("Email called");
		List<User> userlist=userrepo.findAll();
		
		if(!email.contains("@")||!email.contains(".com")) {
			 errors.put("Email", "Invalide Email");
			 return false;
			}
		else {
			  for (User user : userlist) {
				  if(user.getEmail().equals(email)) {
					errors.put("Email", "Invalide Email***");
					return false;
					}
			
			
			}
			  return true;
			  }
		
		
	}
	private boolean LoginChecked(String login) {
		
		List<User> userlist=userrepo.findAll();
		
		 if(login.trim().length()>4&&!login.isEmpty()) {
			 for (User user : userlist) {
					if(user.getLogin().contentEquals(login)) {
						errors.put("login", "Invalide Login");
						return false;	}
				}
			 return true;
		 }
			 
		 else {
			 errors.put("login", "Invalide Login");
			 return false;
		 }
			 
		 }
	private boolean PasswordChecked(String pass) {
		
		
		if(pass.trim().length()<6||pass.isEmpty()) {
			errors.put("Password", "Invalide Password");
			return false;
		}
			
		else {
			return true;
		}
			
		
	}
	

}
