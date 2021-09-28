package sd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import javax.transaction.Transactional;
import java.sql.Date;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class CenterService {

    private CenterEntity center;
    private final CenterRepository centerRepository;
    private final DGSRepository dgsRepository;
    private final PersonRepository personRepository;
    private final NotificationsRepository notificationsRepository;

    @Autowired
    public CenterService(CenterRepository centerRepository, DGSRepository dgsRepository, PersonRepository personRepository, NotificationsRepository notificationsRepository)
    {
        this.centerRepository = centerRepository;
        this.dgsRepository = dgsRepository;
        this.personRepository = personRepository;
        this.notificationsRepository = notificationsRepository;
    }

    @PostMapping
    public void addCenter(String name, String location, Integer maxNVaccines)
    {
        center = new CenterEntity(name, location);

        centerRepository.save(center);

        DGSEntity dgs = new DGSEntity(name, maxNVaccines);

        dgsRepository.save(dgs);

    }
    @GetMapping
    public Optional<List<CenterEntity>> getCenter(String name)
    {
       return centerRepository.findAllByName(name);
    }

    @GetMapping
    public List<CenterEntity> getAllCenters()
    {
        return centerRepository.findAll();
    }

    @PostMapping
    public void insertPersonIntoCenter(String name, String email, Date date)
    {

        List<CenterEntity> list = centerRepository.findAllByName(name)
                .orElseThrow( () -> new IllegalStateException("Center non Existent!\n"));

        PersonEntity person = personRepository.findPersonEntityByEmail(email)
                .orElseThrow( () -> new IllegalStateException("Person non Existent!\n"));

        center = new CenterEntity(name, list.get(0).getLocation(), list.get(0).getnVaccines(), date, person.getId());

        centerRepository.save(center);
    }

    @Transactional
    public void applyVaccine(String email)
    {
        List<PersonEntity> list;
        NotificationsEntity notificationsEntity;

        PersonEntity person;
        person = personRepository.findPersonEntityByEmail(email)
                .orElseThrow( () -> new IllegalStateException("Person non Existent!\n"));

        center = centerRepository.findCenterEntityByPersonId(person.getId())
                .orElseThrow( () -> new IllegalStateException("Center non Existent Or Person Id incorrect!\n"));

        DGSEntity dgs;
        dgs = dgsRepository.findDGSEntityByCenterNameAndDate(center.getName(), person.getVacDate())
                .orElseThrow( () -> new IllegalStateException("There are no available Vaccines for that specific Date!\n"));

        list = personRepository.findPersonEntityByVacDateAndIsVaccinated(person.getVacDate(), false)
                .orElseThrow( () -> new IllegalStateException("Invalid Date!\n"));


            Collections.sort(list, new Comparator<PersonEntity>() {
                @Override
                public int compare(PersonEntity o1, PersonEntity o2) {
                    return o2.getAge() - o1.getAge();
                }
            });

            for (int i = 0; i < list.toArray().length; i++)
            {
                person = personRepository.findPersonEntityByEmail(list.get(i).getEmail())
                        .orElseThrow( () -> new IllegalStateException("Person non Existent!\n"));

                //People who will be vaccinated
                if ( i < dgs.getnVaccines())
                {
                    person.setIsVaccinated(true);
                    person.setVacType("Pfizer");
                    person.setCenterName(center.getName());
                    dgs.setnVaccinated(dgs.getnVaccinated() + 1);
                }
                //People who will be re-scheduled
                else
                {
                    System.out.println(person);

                    Date d = person.getVacDate();

                    String s = d.toString();

                    String arr [] = s.split("-");

                    int day = Integer.parseInt(arr[2]);
                    day++;

                    arr[2] = "";
                    arr[2] += day;

                    String finalDate = arr[0] + "-" + arr[1] + "-" + arr[2];

                    d = Date.valueOf(finalDate);

                    person.setVacDate(d);

                    if (notificationsRepository.findNotificationsEntityByEmail(person.getEmail()).isPresent())
                    {
                        notificationsEntity = notificationsRepository.findNotificationsEntityByEmail(person.getEmail())
                                .orElseThrow( () -> new IllegalStateException("Email not found!\n"));

                        notificationsEntity.setNotificationMsg("Your Vaccination Date has been re-scheduled to the following date: " + person.getVacDate());
                    }
                    else
                    {
                        notificationsEntity = new NotificationsEntity(person.getEmail(), "Your Vaccination Date has been re-scheduled to the following date: " + person.getVacDate());

                        notificationsRepository.save(notificationsEntity);
                    }
                }


            System.out.println(list);
        }
    }

    @GetMapping
    public List<PersonEntity> getVaccinatedFromCenter(String name)
    {
        List<PersonEntity> list;

        list = personRepository.findPersonEntityByCenterNameAndIsVaccinated(name, true)
                .orElseThrow( () -> new IllegalStateException("Center non Existent or None have been vaccinated"));

        return list;
    }
}
