package com.sales.services;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sales.exceptions.InsufficientStockException;
import com.sales.exceptions.NoSuchCustomerException;
import com.sales.exceptions.NoSuchProductException;
import com.sales.models.Order;
import com.sales.repositories.OrderRepository;

@Service
public class OrderService {

	@Autowired
	OrderRepository or;
	@Autowired
	CustomerService cs;
	@Autowired
	ProductService ps;

	public void saveOrder(Order o) throws InsufficientStockException, NoSuchCustomerException, NoSuchProductException{
		try {
			// See if product exists
			ps.searchProducts(o.getProd().getpId());
			// See if customer exists
			cs.searchCustomers(o.getCust().getcId());
		} catch (Exception e) {
			// Handle exception, if either customer or product cannot be found
			throw new NoSuchCustomerException("Customer and/or Product does not exist");
		}
		
		try {
			// Try to save the order
			or.save(o);
		}
		catch (Exception e) {
			// Handle exception, if there is not enough stock to fill the order
			throw new InsufficientStockException("Quantity too large: Product Stock = " + (o.getProd().getQtyInStock() + o.getQty()));
		}
		
	}

	public ArrayList<Order> listAllOrders() {
		return (ArrayList<Order>) or.findAll();
	}

	// Method to set a new quantity on products ordered
	public void setNewQty(Order o) {
		int newQty = o.getProd().getQtyInStock() - o.getQty();
		o.getProd().setQtyInStock(newQty);
	}

	// Method to set date of order
	public void setDate(Order o) {
		// Get date and parse to a string
		String orderDate = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
		o.setOrderDate(orderDate);
	}
}
