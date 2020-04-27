package com.sales.controllers;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.sales.models.Customer;
import com.sales.models.Product;
import com.sales.services.CustomerService;
import com.sales.services.ProductService;

@Controller
@SessionAttributes({"product", "customer"})
public class MainController {
	@Autowired
	ProductService ps;
	@Autowired
	CustomerService cs;

	@RequestMapping(value = "/addProduct.html", method = RequestMethod.GET)
	public String addProductGET(Model model) {
		Product p = new Product();
		model.addAttribute("product", p);
		return "addProduct";
	}

	@RequestMapping(value = "/addProduct.html", method = RequestMethod.POST)
	public String addProductPOST(@ModelAttribute("product") Product p) {
		ps.saveProduct(p);
		return "redirect:showProducts.html";
	}

	@RequestMapping(value = "/showProducts.html", method = RequestMethod.GET)
	public String listProductGET(Model model) {
		ArrayList<Product> products = ps.listAllProducts();
		model.addAttribute("products", products);
		return "showProducts";
	}
	
	@RequestMapping(value = "/addCustomer.html", method = RequestMethod.GET)
	public String addCustomerGET(Model model) {
		Customer c = new Customer();
		model.addAttribute("customer", c);
		return "addCustomer";
	}

	@RequestMapping(value = "/addCustomer.html", method = RequestMethod.POST)
	public String addCustomerPOST(@ModelAttribute("customer") Customer c) {
		cs.saveCustomer(c);
		return "redirect:showCustomers.html";
	}

	@RequestMapping(value = "/showCustomers.html", method = RequestMethod.GET)
	public String listCustomerGET(Model model) {
		ArrayList<Customer> customers = cs.listAllCustomers();
		model.addAttribute("customers", customers);
		return "showCustomers";
	}

}
