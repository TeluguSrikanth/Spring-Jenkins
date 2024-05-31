package com.hostwire.jwt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hostwire.jwt.dto.Products;
import com.hostwire.jwt.entity.UserInfi;
import com.hostwire.jwt.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {

	 @Autowired
	    private ProductService service;
	
	@GetMapping("/welcome")
	
    public String welcome() {
        return "Welcome this endpoint is not secure";
    }

	 @PostMapping("/new")
	    public String addNewUser(@RequestBody UserInfi userInfo){
	        return service.addUser(userInfo);
	    }

	    @GetMapping("/all")
	    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
	    public List<Products> getAllTheProducts() {
	        return service.getProducts();
	    }

	    @GetMapping("/{id}")
	    @PreAuthorize("hasAuthority('ROLE_USER')")
	    public Products getProductById(@PathVariable int id) {
	        return service.getProduct(id);
	    }
}
