package com.sales.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sales.models.Product;
import com.sales.repositories.ProductRepositiory;

@Service
public class ProductService {
	@Autowired
	ProductRepositiory pr;
	
	public void saveProduct(Product p) {
		pr.save(p);
	}
	
	public ArrayList<Product> listAllProducts(){
		return (ArrayList<Product>) pr.findAll();
	}
	
	public Product searchProducts(long pId) {
		return pr.findOne(pId);
	}
}
