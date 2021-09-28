package sd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value="/center")
public class CenterController {

    private final CenterService centerService;


    @Autowired
    public CenterController(CenterService centerService)
    {
        this.centerService = centerService;
    }

    @PostMapping(value="/addCenter")
    public void addCenter(@PathParam("name") String name, @PathParam("location") String location, @PathParam("n_vaccines") Integer n_vaccines)
    {
        centerService.addCenter(name, location, n_vaccines);
    }

    @GetMapping(value="/getCenter")
    public Optional<List<CenterEntity>> getCenter(@PathParam("name") String name)
    {
        return centerService.getCenter(name);
    }

    @GetMapping(value="/getAllCenters")
    public List<CenterEntity> getAllCenters()
    {
        return centerService.getAllCenters();
    }

    @PostMapping(value="/insertPersonIntoCenter")
    public void insertPersonIntoCenter(@PathParam("name") String name, @PathParam("email") String email, @PathParam("date") Date date)
    {
        centerService.insertPersonIntoCenter(name, email, date);
    }

    @PutMapping(value="/applyVaccine")
    public void applyVaccine(@PathParam("email") String email)
    {
        centerService.applyVaccine(email);
    }

    @GetMapping(value="/getVaccinated")
    public List<PersonEntity> getVaccinatedFromCenter(@PathParam("name") String name)
    {
        return centerService.getVaccinatedFromCenter(name);
    }
}
