package com.hostwire.jwt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.hostwire.jwt.dto.Products;
import com.hostwire.jwt.entity.UserInfi;
import com.hostwire.jwt.repository.UserInfiRepository;

@Service
public class ProductService {

	List<Products> productList = null;

    @Autowired
    private UserInfiRepository repository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;


	public String addUser(UserInfi userInfo) {
		// TODO Auto-generated method stub
		userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
        repository.save(userInfo);
        return "user added to system ";
	}

	public List<Products> getProducts() {
		// TODO Auto-generated method stub
		return productList;
	}

	public Products getProduct(int id) {
		// TODO Auto-generated method stub
		return productList.stream()
                .filter(product -> product.getProductId() == id)
                .findAny()
                .orElseThrow(() -> new RuntimeException("product " + id + " not found"));
    
	}
	
}
