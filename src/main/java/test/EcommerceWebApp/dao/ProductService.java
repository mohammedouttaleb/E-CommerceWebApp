package test.EcommerceWebApp.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import test.EcommerceWebApp.model.Product;
import test.EcommerceWebApp.repositories.ProductRepository;


// SET GLOBAL time_zone = '+5:30';

@Component
public class ProductService {

	@Autowired
	private ProductRepository prodrepo;
	
	//find all product
	public List<Product> FindAllProduct(){
		
		return prodrepo.findAll();
	}
	
	//save a product into data base
	public  Product   SaveProduct(Product product) {
		
		 return prodrepo.save(product);
	}
	
 	 
 	public Product FindOneProduct(int id) {
		
 		try {
 			return prodrepo.findById(id).orElse(null);
 		}catch(Exception e) {
 			e.printStackTrace();
 		    return null;}
		
	}
 	
 	
 	public List<Product > FindByCategorie(String cat) {
 		
 		return prodrepo.findByCatOldProducts(cat);
 	}
 	
 	public List<Product> FindLastProducts(String cat){
 		
 		return prodrepo.LastProductAdded(cat); 
 	}
 	
 	public void DeleteProduct(int productid) {
 		
 		Product prod=FindOneProduct(productid);
 		if(!prod.equals(null))
 		prodrepo.delete(prod);
 	}

 	
 	
 	public List<Product> SortProductBySellingNumbers() {
 	    return prodrepo.test();
 	}
 	
 	
 	
 	
	public boolean UpdateStock(Product prod,int quantite) {
      
		if(!(prod.equals(null))&&prod.getStock()!=0) {
			if(prod.getStock()-quantite<0)
				 return false;
			else {
				prod.setStock(prod.getStock()-quantite);
				prod.setSellingnumber(prod.getSellingnumber()+quantite);
				System.out.println("prod stock!!! :"+prod.getStock());
				return true;
			}
		}
		else
			 return false;
	}
	
	
	
}
