package test.EcommerceWebApp.controllers;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import test.EcommerceWebApp.dao.ProductService;
import test.EcommerceWebApp.dao.UserService;
import test.EcommerceWebApp.model.Product;
import test.EcommerceWebApp.model.User;

@Controller
public class ProductController {
	
	private final String  User_name="user";
	private final String  homepagename="home";
	private final String  allproductname="products";
	private final String  detailspagename="product_detail";
	private final String  productname="product";
	private final String  productformname="productform";
	private final String  validationname="validationproduct";
	private final String  validationbuyer="validationbuy";
	private final String  productscatgeriename="categories";
	private final String  errorname="errors";
	public static String uploadDirectory=System.getProperty("user.dir")+"/src/main/resources/static/assets/img";
	@Autowired
	private ProductService productservice;
	@Autowired
	private UserService userservice;
	
	
	

	@GetMapping("/librarystore")
	public String HomePage(Model model) {
		
	   model.addAttribute(allproductname,productservice.SortProductBySellingNumbers());
	   
	   return homepagename;
		}
	
	
	
	@GetMapping("/products/{categorie}")
	public String GetProductsByCategory(@PathVariable("categorie") String categorie,Model model) {
		List<Product> productlist=productservice.FindByCategorie(categorie);
		List<Product> lastaddedproductlist=productservice.FindLastProducts(categorie);
		System.out.println("old products : "+productlist);
		model.addAttribute("prodbycategorie",productlist);
		model.addAttribute("lastproducts",lastaddedproductlist);
		model.addAttribute("categorie",categorie); 
	  	return productscatgeriename;
	}
	
	
	@GetMapping("/productdetails/{id}")
	
	public String PublicProductsDetails(@PathVariable("id") int id,Model model) {

		model.addAttribute(productname, productservice.FindOneProduct(id));
		return detailspagename;
	}	
	
	@PostMapping("/buyproduct/{id}")
    public String BuyProduct(@PathVariable("id") int prodid,int quantite,Model model) {
    	
		Product prod=productservice.FindOneProduct(prodid);
		if(!productservice.UpdateStock(prod, quantite))
			model.addAttribute(errorname,"there is no avaible stock for ur commande Sorry!!");
		productservice.SaveProduct(prod);
		model.addAttribute(productname,prod);
		return validationbuyer;
    }

	
	
	
	
	//get a product details by  id --RestApi methode--
	@GetMapping("/user/{userid}/productdetails/{id}")
	public String ProductDetails(@PathVariable("userid") int userid,@PathVariable("id") int id ,Model model) {
        
		User user=userservice.FindUserById(userid);
		model.addAttribute(User_name,user);
		model.addAttribute(productname, productservice.FindOneProduct(id));
		return detailspagename;
	}
	
	
	
	@GetMapping("/user/{id}/addproduct")
	public String AddProduct(@PathVariable("id") int userid,Model model) {
    	model.addAttribute("userid", userid);
    	return productformname;
    }
    
    
	
	@GetMapping("/user/{userid}/delete/{id}")
	public String DeleteProduct(@PathVariable("userid") int userid,@PathVariable("id") int productid,Model model) {
    	User user=userservice.FindUserById(userid);
		productservice.DeleteProduct(productid);
		model.addAttribute(User_name,user);
		return validationname;
    }
	
	@PostMapping("/user/{id}/sendproduct")
    public String SendProduct(@PathVariable("id") int userid,Product product,@RequestParam("uploadfile") MultipartFile[] imagefile,Model model) {
    	
    	User user=userservice.FindUserById(userid);
    	product.setUser(user);
    	
    	product.setDateDepot(LocalDateTime.now());
    	
    	/*code of upload the product image*/
    	
	    StringBuilder filenames=new StringBuilder();
		int counter=1;
		for (MultipartFile file : imagefile) {
			
			new File(ProductController.uploadDirectory+"/"+product.getName().trim()).mkdir();
			Path path = Paths.get(uploadDirectory+"/"+product.getName().trim(),counter+".jpg");
             filenames.append(file.getOriginalFilename());
            
             try {
				Files.write(path, file.getBytes());
				counter++;
			} catch (IOException e) {
				model.addAttribute(errorname, e.getMessage());}
		}
    	/*end of the code*/
		
    	productservice.SaveProduct(product);
    	model.addAttribute(productname, product);
    	model.addAttribute(User_name,user);
    	return validationname;
    
    }
    
    
}
