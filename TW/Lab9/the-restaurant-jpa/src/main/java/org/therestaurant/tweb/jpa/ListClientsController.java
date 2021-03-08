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
public class ListClientsController {

	private static final Logger log = LoggerFactory.getLogger(ListClientsController.class);

    @Autowired
    private ClientRepository repository;
    
	@GetMapping("/list-clients")
	public String listClients(Model model) 
	{		
		List<Client> customerList = (List<Client>) repository.findAll();
		
		log.info("Customers found with findAll():");
		log.info("-------------------------------");
		for (Client client : repository.findAll()) {
			log.info(client.toString());
		}
		log.info("");
		
		model.addAttribute("customerList", customerList);
		return "list-clients-view";
	}
}
