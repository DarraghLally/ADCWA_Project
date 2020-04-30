package com.sales.controllers;


import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.sales.exceptions.InsufficientStockException;
import com.sales.exceptions.NoSuchCustomerException;
import com.sales.exceptions.NoSuchProductException;
import com.sales.models.Customer;
import com.sales.models.Order;
import com.sales.models.Product;
import com.sales.services.CustomerService;
import com.sales.services.OrderService;
import com.sales.services.ProductService;

@Controller
@SessionAttributes({ "product", "customer", "order", "customerNames", "productNames" })
public class MainController {
	@Autowired
	ProductService ps;
	@Autowired
	CustomerService cs;
	@Autowired
	OrderService os;

	// ====================================================================
	// == Products ==
	// ====================================================================
	@RequestMapping(value = "/addProduct.html", method = RequestMethod.GET)
	public String addProductGET(Model model) {
		Product p = new Product();
		model.addAttribute("product", p);
		return "addProduct";
	}

	@RequestMapping(value = "/addProduct.html", method = RequestMethod.POST)
	public String addProductPOST(@Valid @ModelAttribute("product") Product p, BindingResult br) {
		if(br.hasErrors()){
			return "addProduct";
		}
		ps.saveProduct(p);
		return "redirect:showProducts.html";
	}

	@RequestMapping(value = "/showProducts.html", method = RequestMethod.GET)
	public String listProductGET(Model model) {
		ArrayList<Product> products = ps.listAllProducts();
		model.addAttribute("products", products);
		return "showProducts";
	}

	// ====================================================================
	// == Customers ==
	// ====================================================================
	@RequestMapping(value = "/addCustomer.html", method = RequestMethod.GET)
	public String addCustomerGET(Model model) {
		Customer c = new Customer();
		model.addAttribute("customer", c);
		return "addCustomer";
	}

	@RequestMapping(value = "/addCustomer.html", method = RequestMethod.POST)
	public String addCustomerPOST(@Valid @ModelAttribute("customer") Customer c, BindingResult br) {
		if(br.hasErrors()) {
			return "addCustomer";
		}
		cs.saveCustomer(c);
		return "redirect:showCustomers.html";
	}

	@RequestMapping(value = "/showCustomers.html", method = RequestMethod.GET)
	public String listCustomerGET(Model model) {
		ArrayList<Customer> customers = cs.listAllCustomers();
		model.addAttribute("customers", customers);
		return "showCustomers";
	}

	// ====================================================================
	// == Orders ==
	// ====================================================================
	@RequestMapping(value = "/newOrder.html", method = RequestMethod.GET)
	public String newOrderGET(Model model) {
		// Customer Names
		ArrayList<Customer> cust = cs.listAllCustomers();
		Map<Long, String> customerNames = new LinkedHashMap<Long, String>();
		for (Customer c : cust) {
			customerNames.put(c.getcId(), c.getcName());
		}
		model.addAttribute("customerNames", customerNames);

		// Product Descriptions
		ArrayList<Product> prod = ps.listAllProducts();
		Map<Long, String> productNames = new LinkedHashMap<Long, String>();
		for (Product p : prod) {
			productNames.put(p.getpId(), p.getpDesc());
		}
		model.addAttribute("productNames", productNames);
		
		// Order
		Order o = new Order();
		model.addAttribute("order", o);
		return "newOrder";
	}

	@RequestMapping(value = "/newOrder.html", method = RequestMethod.POST)
	public String newOrderPOST(@Valid @ModelAttribute("order") Order o, BindingResult br, Model m) {
		if (br.hasErrors()) {
			return "newOrder";
		}
		try {
			// Set Date on Order
			os.setDate(o);
			// Set new Stock quantity
			os.setNewQty(o);
			// Save the order
			os.saveOrder(o);
			return "redirect:showOrders.html";
		}catch(InsufficientStockException e) {
			m.addAttribute("error", e);
			return "errorWithOrder";
		}catch(NoSuchCustomerException e) {
			m.addAttribute("error", e);
			return "errorWithOrder";
		}catch(NoSuchProductException e) {
			m.addAttribute("error", e);
			return "errorWithOrder";
		}	
	}

	@RequestMapping(value = "/showOrders.html", method = RequestMethod.GET)
	public String listOrderGET(Model model) {
		ArrayList<Order> orders = os.listAllOrders();
		model.addAttribute("orders", orders);
		return "showOrders";
	}

	// ====================================================================
	// == Logout ==
	// ====================================================================
	@RequestMapping(value="/logout", method = RequestMethod.GET)
	public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
	    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    if (auth != null){    
	        new SecurityContextLogoutHandler().logout(request, response, auth);
	    }
	    return "redirect:/login?logout"; //You can redirect wherever you want, but generally it's a good practice to show login screen again.
	}
	
}
