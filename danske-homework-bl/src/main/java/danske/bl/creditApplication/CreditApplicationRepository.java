package danske.bl.creditApplication;

import danske.CreditApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

@NoRepositoryBean
public interface CreditApplicationRepository extends JpaRepository<CreditApplication, Long> {

    List<CreditApplication> getCreditApplicationsByCustomerId(Long customerId);

}
