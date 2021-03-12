package test.EcommerceWebApp.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Product {

	@Id
	@GeneratedValue(strategy =GenerationType.AUTO)
	private int id;
	
	private String brand;
	private String name;
	private int price;
	private String description;
	private int stock;
	private int sellingnumber;
	private String categorie;
	private LocalDateTime dateDepot;
	

	@ManyToOne
	
	private User user;
	
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getId() {
		return id;
	}
	
	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock =stock;
	}
	public String getCategorie() {
		return categorie;
	}
	public void setCategorie(String cat) {
		this.categorie = cat;
	}
	

	public int getSellingnumber() {
		return sellingnumber;
	}

	public void setSellingnumber(int selling_number) {
		this.sellingnumber = selling_number;
	}
	
	
	

	public LocalDateTime getDateDepot() {
		return dateDepot;
	}

	public void setDateDepot(LocalDateTime dateDepot) {
		this.dateDepot = dateDepot;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", price=" + price + ", description=" + description
				+ ", stock=" + stock + ", categorie=" + categorie + ", user=" + user + "]";
	}
	
	
	
	
	
	
}
