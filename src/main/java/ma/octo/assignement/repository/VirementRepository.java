package ma.octo.assignement.repository;

import ma.octo.assignement.domain.Virement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Date;

@Repository
public interface VirementRepository extends JpaRepository<Virement, Long> {

    int countByCompteBeneficiairRibAndDateExecution(String rib ,Date date);
}
