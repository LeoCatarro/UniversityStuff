package org.therestaurant.tweb.jpa;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class NewClientController {

	private static final Logger log = LoggerFactory.getLogger(NewClientController.class);

    @Autowired
    private ClientRepository repository;
    
	@PostMapping("/new-client")
	public String newClient(
			@RequestParam(name="firstName", required=false, defaultValue="World") String firstName, 
			@RequestParam(name="lastName", required=false, defaultValue="") String lastName,
			@RequestParam(name="mail", required=false, defaultValue="") String mail,
			Model model) 
	{
		
		repository.save(new Client(firstName, lastName, mail));
		
		log.info("Customers found with findAll():");
		log.info("-------------------------------");
		for (Client aClient : repository.findAll()) {
			log.info(aClient.toString());
		}
		log.info("");
		
		model.addAttribute("name", firstName);
		model.addAttribute("surname", lastName);
		model.addAttribute("mail", mail);
		return "new-client-view";
	}
}
