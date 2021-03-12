package test.EcommerceWebApp.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private String login;
	private String password;
	private String email;
	
	@OneToMany
	@JoinColumn(name = "user_id")
	private Set<Product> productlist=new HashSet<Product>();
	
	
	
	public int getId() {
		return id;
	}
	
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
	
	
	public Set<Product> getProductlist() {
		return productlist;
	}

	public void setProductlist(Set<Product> productlist) {
		this.productlist = productlist;
	}

	

	
	
	
	

}
