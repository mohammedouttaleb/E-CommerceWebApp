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





















    /*methode pour valider l'inscription d'un nouveau user*/
	
	public boolean  UserCheck(User user) {
		
		if(user.equals(null)) {
			//System.out.println("madkhell");
			return false;
		}
			
		else {
			boolean statut=true;
			if(!this.getErrors().isEmpty())
				this.getErrors().clear();
		  
			if(!EmailChecked(user.getEmail())) {
				System.out.println("madkhell1");
				
				//System.out.println(errors.toString());
				statut=false;
				
			}
			 if(!LoginChecked(user.getLogin())) {
				
				System.out.println("madkhel2");
		
				//System.out.println(errors.toString());
				statut=false;
				
			}
			 if(!PasswordChecked(user.getPassword())) {
				
				System.out.println("madkhel3");
				
				//System.out.println(errors.toString());
				statut=false;
			}
			else {
				 System.out.println("dkhell"); 
			     //userrepo.save(user);
			}
			 
			return statut;}
		}
	
	
	/*methode pour checker lelogin est valide ou nons*/
	public User UserExists(String login,String Password) {
		
		List <User> userlist=userrepo.findAll();
		if(!userlist.equals(null)) {
		
			for (User user : userlist) {
				   if(user.getLogin().equals(login)&&user.getPassword().equals(Password)) {
					   //System.out.println("trouvable");
					   return user;
				   }
				}
				errors.put("loginerror", "Login or Password Invalide");
				//System.out.println("errors "+errors);
				//System.out.println("introuvable");
				return null;
		}
		errors.put("loginerror", "Login or Password Invalide");
		//System.out.println("errors "+errors);
		//System.out.println("introuvable");
		 return null;
			
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	private boolean EmailChecked(String email) {
		System.out.println("Email called");
		List<User> userlist=userrepo.findAll();
		
		if(!email.contains("@")||!email.contains(".com")) {
			 System.out.println("Email Invalide");
			 //errors.add("Invalide Email");
			 errors.put("Email", "Invalide Email");
			 return false;
			}
		else {
			  for (User user : userlist) {
				  if(user.getEmail().equals(email)) {
					System.out.println("Invalide Email**");
					//errors.add("Invalide Email");
					errors.put("Email", "Invalide Email***");
					//System.out.println("errors "+errors.toString());
					return false;
					}
			
			
			}
			  //System.out.println("valide Email");
			  return true;
			  }
		
		
	}
	private boolean LoginChecked(String login) {
		//System.out.println("Login called");
		List<User> userlist=userrepo.findAll();
		
		 if(login.trim().length()>4&&!login.isEmpty()) {
			 for (User user : userlist) {
					if(user.getLogin().contentEquals(login)) {
						System.out.println("Invalide login");
						//errors.add("Invalide Login");
						errors.put("login", "Invalide Login");
						//System.out.println(errors.toString());
						return false;	}
				}
			 System.out.println("valide Login");
			 return true;
		 }
			 
		 else {
			 System.out.println("Invalide login");
			 //errors.add("Invalide Login");
			 errors.put("login", "Invalide Login");
			 //System.out.println(errors.toString());
			 return false;
		 }
			 
		 }
	private boolean PasswordChecked(String pass) {
		
		//System.out.println("Pass called");
		if(pass.trim().length()<6||pass.isEmpty()) {
			System.out.println("Invalide pass");
			//errors.add("Invalide Password");
			errors.put("Password", "Invalide Password");
			System.out.println(errors.toString());
			return false;
		}
			
		else {
			System.out.println("valide pass");
			return true;
		}
			
		
	}
	

}
