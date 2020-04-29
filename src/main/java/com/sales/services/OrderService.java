package com.sales.services;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sales.exceptions.InsufficientStockException;
import com.sales.models.Order;
import com.sales.repositories.OrderRepository;

@Service
public class OrderService {

	@Autowired
	OrderRepository or;

	public void saveOrder(Order o) throws InsufficientStockException{
		try {
			or.save(o);
		}
		catch (Exception e) {
			throw new InsufficientStockException("Quantity Too Large: Product Stock = " + (o.getProd().getQtyInStock() + o.getQty()));
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
