package test.EcommerceWebApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import test.EcommerceWebApp.model.User;


public interface Userrepository extends JpaRepository<User, Integer> {
	
}
