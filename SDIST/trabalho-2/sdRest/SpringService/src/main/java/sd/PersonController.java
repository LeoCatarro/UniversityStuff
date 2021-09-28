package sd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/person")
public class PersonController {

    PersonEntity person;
    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService)
    {
        this.personService = personService;
    }

    @GetMapping
    public List<PersonEntity> getPeople()
    {
        return personService.getPeople();
    }

    @GetMapping(value = "/getPerson")
    public boolean getPerson(@PathParam("email") String email)
    {
        return personService.getPerson(email);
    }

    @PostMapping(value ="/insertPerson", produces = "application/json")
    public void registerPerson(@PathParam("name") String name, @PathParam("age") Integer age, @PathParam("email") String email, @PathParam("date") Date date, @PathParam("centerName") String centerName)
    {
        personService.registerPerson(name, age, email, date, centerName);
    }

    @PutMapping(value="/testPut")
    public void updatePerson(@PathParam("name") String name, @PathParam("age") Integer age, @PathParam("currEmail") String currEmail, @PathParam("newEmail") String newEmail)
    {
        personService.updatePerson(name, age, currEmail, newEmail);
    }
}