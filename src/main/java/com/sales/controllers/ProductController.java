package com.sales.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.sales.models.Product;

@Controller
@SessionAttributes("product")
public class ProductController {

  @RequestMapping(value = "/addProduct.html", method = RequestMethod.GET)
  public String addProductGET(Model model) {
	  Product p = new Product();
	  model.addAttribute("product", p);
    
    return "addProduct";
  }
  
  @RequestMapping(value = "/addProduct.html", method = RequestMethod.POST)
  public String addProductPOST(@ModelAttribute("product") Product p) {
    
    return "redirect:listProducts.html";
  }
  
  @RequestMapping(value = "/listProducts.html", method = RequestMethod.GET)
  public String addProductGET(@ModelAttribute("product") Product p) {
    
    return "listProducts";
  }
  
}
