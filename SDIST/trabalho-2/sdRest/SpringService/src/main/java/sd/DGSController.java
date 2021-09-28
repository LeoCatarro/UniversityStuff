package sd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping(value="/dgs")
public class DGSController {

    private final DGSService dgsService;

    @Autowired
    public DGSController(DGSService dgsService)
    {
        this.dgsService = dgsService;
    }

    @PostMapping(value="/registerCenter")
    public void registerCenter(CenterEntity center)
    {
        dgsService.registerCenter(center);
    }

    @PutMapping(value="/updateDateAndVaccines")
    public void updateDateAndVaccines(@PathParam("name") String name, @PathParam("n_vaccines") Integer n_vaccines, @PathParam("date") Date date)
    {
        dgsService.updateDateAndVaccines(name, n_vaccines, date);
    }

    @GetMapping(value = "/getVaccinatedPerDay")
    public List<PersonEntity> getVaccinatedPerDay(@PathParam("date") Date date)
    {
        return dgsService.getVaccinatedPerDay(date);
    }

    @GetMapping(value = "/getListOfPeopleToBeVaccinatedPerDay")
    public List<PersonEntity> getListOfPeopleToBeVaccinatedPerDay(@PathParam("date") Date date)
    {
        return dgsService.getListOfPeopleToBeVaccinatedPerDay(date);
    }

}
