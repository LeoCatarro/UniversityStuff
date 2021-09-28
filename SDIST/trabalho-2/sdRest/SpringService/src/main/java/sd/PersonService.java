package sd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import javax.transaction.Transactional;
import javax.websocket.server.PathParam;
import java.sql.Date;
import java.util.List;

@Service
public class PersonService {

    private PersonEntity person;

    private final PersonRepository personRepository;
    private final DGSRepository dgsRepository;

    @Autowired
    public PersonService(PersonRepository personRepository, DGSRepository dgsRepository)
    {
        this.personRepository = personRepository;
        this.dgsRepository = dgsRepository;
    }

    @GetMapping
    public List<PersonEntity> getPeople()
    {
        return personRepository.findAll();
    }

    @PostMapping
    public void registerPerson(String name, Integer age, String email, Date date, String centerName)
    {
        person = new PersonEntity(name, age, email, date, centerName);

        if (dgsRepository.findDGSEntityByCenterNameAndDate(centerName, date).isPresent())
        {
            DGSEntity dgs = dgsRepository.findDGSEntityByCenterNameAndDate(centerName, date)
                    .orElseThrow( () -> new IllegalStateException("This was not suppose to enter here"));

            if ((dgs.getnVaccines() == dgs.getnVaccinated()) && dgs.getnVaccinated() != 0)
            {
                throw new IllegalStateException("This day is already full cannot make an appointment, choose another date");
            }
            else
            {
                //email present in the repository in some user
                if (personRepository.findPersonEntityByEmail(person.getEmail()).isPresent())
                {
                    throw new IllegalStateException("Email Taken");
                }

                personRepository.save(person);
            }
        }
        else
        {
            //email present in the repository in some user
            if (personRepository.findPersonEntityByEmail(person.getEmail()).isPresent())
            {
                throw new IllegalStateException("Email Taken");
            }

            personRepository.save(person);
        }
    }

    @Transactional
    public void updatePerson(String name, Integer age, String currEmail, String newEmail)
    {
        person = personRepository.findPersonEntityByEmail(currEmail)
                .orElseThrow( () -> new IllegalStateException("Email does not exist!\n"));

        //If name is valid and not the same as the already in DB insert
        if (name != null && name.length() > 0 && !person.getName().equals(name))
        {
            person.setName(name);
        }

        //If email is valid and not the same as the already in DB insert
        if (newEmail != null && newEmail.length() > 0 && !person.getEmail().equals(newEmail))
        {
            person.setEmail(newEmail);
        }

        //If age is valid and not the same as the already in DB insert
        if (age != 0 && person.getAge() != age)
        {
            person.setAge(age);
        }
    }

    @GetMapping
    public boolean getPerson(String email) {

        if (personRepository.findPersonEntityByEmail(email).isPresent())
            return true;

        else
            return false;
    }
}
