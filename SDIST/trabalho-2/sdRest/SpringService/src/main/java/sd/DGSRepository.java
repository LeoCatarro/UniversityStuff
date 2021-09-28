package sd;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface DGSRepository extends JpaRepository<DGSEntity, Integer> {

    Optional<DGSEntity> findDGSEntityByCenterName(String centerName);
    Optional<List<DGSEntity>> findAllByCenterName(String centerName);
    Optional<DGSEntity> findDGSEntityByCenterNameAndDate(String centerName, Date date);
}
