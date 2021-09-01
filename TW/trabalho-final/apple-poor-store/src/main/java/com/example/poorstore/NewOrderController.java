package com.example.poorstore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class NewOrderController {

    @Autowired
    private ClientRepository clientRepository;
    
    @Autowired
    private OrdersRepository ordersRepository;
    
    @PostMapping("/new-order")
	public String newOrder(
			@RequestParam(name="clientID", required=false) long clientID, 
			@RequestParam(name="entry", required=false, defaultValue="") String entry,
			@RequestParam(name="mainCourse", required=false, defaultValue="") String mainCourse,
			@RequestParam(name="drink", required=false, defaultValue="") String drink,
			@RequestParam(name="dessert", required=false, defaultValue="") String dessert,
			Model model) 
	{
		
		Client client = clientRepository.findById(clientID);
		ordersRepository.save(new Orders(client, entry, mainCourse, drink, dessert));

		model.addAttribute("clientID", clientID);
		model.addAttribute("entry", entry);
		model.addAttribute("mainCourse", mainCourse);
		model.addAttribute("drink", drink);
		model.addAttribute("dessert", dessert);

		return "new-order-view";
	}
}
