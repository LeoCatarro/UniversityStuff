package com.example.poorstore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class NewClientController {

	private static final Logger log = LoggerFactory.getLogger(NewClientController.class);

    @Autowired
    private ClientRepository clientRepository;

	public PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    
	@PostMapping("/new-client")
	public String newClient(
			@RequestParam(name="firstName", required=false, defaultValue="World") String firstName, 
			@RequestParam(name="lastName", required=false, defaultValue="") String lastName,
			@RequestParam(name="mail", required=false, defaultValue="") String mail,
			@RequestParam(name="username", required=false, defaultValue="") String username,
			@RequestParam(name="password", required=false, defaultValue="") String password,
			Model model) 
	{

		clientRepository.save(new Client(firstName, lastName, mail, username, passwordEncoder.encode(password), "ROLE_USER"));
		
		log.info("Customers found with findAll():");
		log.info("-------------------------------");
		for (Client aClient : clientRepository.findAll()) {
			log.info(aClient.toString());
		}
		log.info("");
		
		model.addAttribute("name", firstName);
		model.addAttribute("surname", lastName);
		model.addAttribute("mail", mail);
		model.addAttribute("username", username);
		model.addAttribute("password", password);

		return "login";
	}
}
