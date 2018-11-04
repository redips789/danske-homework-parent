package danske.bl.creditApplication.impl;

import danske.CreditApplication;
import danske.CreditApplication_;
import danske.Customer;
import danske.Customer_;
import danske.bl.creditApplication.CreditApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.JpaEntityInformationSupport;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Component("creditApplicationRepositoryImpl")
public class CreditApplicationRepositoryImpl extends SimpleJpaRepository<CreditApplication, Long> implements CreditApplicationRepository {
    @PersistenceContext
    private EntityManager em;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public CreditApplicationRepositoryImpl(EntityManager entityManager) {
        super(JpaEntityInformationSupport.getEntityInformation(CreditApplication.class, entityManager), entityManager);
    }

    @Override
    public List<CreditApplication> getCreditApplicationsByCustomerId(Long id) {
        return findAll((root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get(CreditApplication_.customer).get(Customer_.id), id));
    }
}
