package danske.bl.creditApplication.impl;

import danske.CreditApplication;
import danske.CreditApplication_;
import danske.Customer_;
import danske.bl.creditApplication.CreditApplicationRepository;
import org.springframework.data.jpa.repository.support.JpaEntityInformationSupport;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Component("creditApplicationRepositoryImpl")
public class CreditApplicationRepositoryImpl extends SimpleJpaRepository<CreditApplication, Long> implements CreditApplicationRepository {

    public CreditApplicationRepositoryImpl(EntityManager entityManager) {
        super(JpaEntityInformationSupport.getEntityInformation(CreditApplication.class, entityManager), entityManager);
    }

    @Override
    public List<CreditApplication> getCreditApplicationsByCustomerId(Long customerId) {
        return findAll((root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get(CreditApplication_.customer).get(Customer_.id), customerId));
    }
}
