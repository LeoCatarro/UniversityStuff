package sd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.transaction.Transactional;
import java.sql.Date;
import java.util.List;

@Service
public class DGSService {

    private DGSEntity dgs;
    private final DGSRepository dgsRepository;
    private final PersonRepository personRepository;

    @Autowired
    public DGSService(DGSRepository dgsRepository, PersonRepository personRepository)
    {
        this.dgsRepository = dgsRepository;
        this.personRepository = personRepository;
    }

    @PostMapping
    public void registerCenter(CenterEntity center)
    {
        dgs = new DGSEntity(center.getName(), center.getnVaccines());

        dgsRepository.save(dgs);
    }



    @Transactional
    public void updateDateAndVaccines(String name, Integer nVaccines, Date date)
    {
        List<DGSEntity> list = dgsRepository.findAllByCenterName(name)
                .orElseThrow( () -> new IllegalStateException("Center non Existant!\n"));

        //If there is a center with Name and Date -> NOT null
        if(!dgsRepository.findDGSEntityByCenterNameAndDate(name, null).isPresent() && !dgsRepository.findDGSEntityByCenterNameAndDate(name, date).isPresent())
        {
            dgs = new DGSEntity(name, nVaccines, date);

            for (DGSEntity dgsEntity : list)
            {

                if (!dgsEntity.getMaxNVaccines().equals(null))
                {
                    dgs.setMaxNVaccines(dgsEntity.getMaxNVaccines());
                    break;
                }
            }

            dgsRepository.save(dgs);
        }
        else
        {
            dgs = dgsRepository.findDGSEntityByCenterNameAndDate(name, null)
                    .orElseThrow( () -> new IllegalStateException("Error"));

            dgs.setnVaccines(nVaccines);
            dgs.setDate(date);
        }
    }

    @GetMapping
    public List<PersonEntity> getVaccinatedPerDay(Date date)
    {
        return personRepository.findPersonEntityByVacDateAndIsVaccinated(date, true)
                .orElseThrow( () -> new IllegalStateException("None has been Vaccinated at all or on this specific Date!\n"));
    }

    @GetMapping
    public List<PersonEntity> getListOfPeopleToBeVaccinatedPerDay(Date date)
    {
        return personRepository.findPersonEntityByVacDateAndIsVaccinated(date, false)
                .orElseThrow( () -> new IllegalStateException("None appointed to be Vaccinated on this specific Date!\n"));
    }
}