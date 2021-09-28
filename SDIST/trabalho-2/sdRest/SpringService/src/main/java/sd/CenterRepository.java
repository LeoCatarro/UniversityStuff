package sd;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface CenterRepository extends JpaRepository<CenterEntity, Integer> {

    Optional<CenterEntity> findCenterEntityById(Integer id);
    Optional<CenterEntity> findCenterEntityByName(String name);
    Optional<CenterEntity> findCenterEntityByPersonId(Integer personId);
    Optional<List<CenterEntity>> findAllByName(String name);

}
