package org.therestaurant.tweb.jpa;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class ListOrdersController {

    @Autowired
    private OrdersRepository repository;
	
	@GetMapping("/list-orders")
	public String listOrders(Model model) 
	{
		List<Orders> ordersList = (List<Orders>) repository.findAll();

		model.addAttribute("ordersList", ordersList);
		System.out.print(ordersList);
		return "list-orders-view";
	}
}
