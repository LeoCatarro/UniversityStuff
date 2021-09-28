package sd;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

public interface PersonRepository extends JpaRepository<PersonEntity, Integer> {

    Optional<PersonEntity> findPersonEntityByEmail(String email);
    Optional<PersonEntity> findPersonEntityById(Integer id);
    Optional<PersonEntity> findPersonEntityByName(String name);
    Optional<List<PersonEntity>> findPersonEntityByCenterNameAndIsVaccinated(String centerName, boolean isVaccinated);
    Optional<List<PersonEntity>> findPersonEntityByVacDateAndIsVaccinated(Date date, boolean isVaccinated);
}
