package test.EcommerceWebApp.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import test.EcommerceWebApp.model.Product;

public interface ProductRepository  extends JpaRepository<Product, Integer>{

	
	
	public List<Product> findByCategorie(String cat);
	
	
	@Query(value="select * from product  order by sellingnumber desc limit 10", nativeQuery = true)
	public List<Product> test();


    @Query(value ="select * from product where categorie=?1 and datediff(sysdate(),date_depot)=0",nativeQuery = true)
	public List<Product> LastProductAdded(String cat);
 

    @Query(value="select * from product where categorie=?1 and datediff(sysdate(),date_depot)!=0",nativeQuery = true)
    public List<Product> findByCatOldProducts(String cat);
    
    
}
