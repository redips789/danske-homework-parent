package danske.bl.loanTerms.impl;

import danske.LoanTerms;
import danske.LoanTerms_;
import danske.bl.loanTerms.LoanTermsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.JpaEntityInformationSupport;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.transaction.Transactional;

@Transactional
@Component("loanTermsRepositoryImpl")
public class LoanTermsRepositoryImpl extends SimpleJpaRepository<LoanTerms, Long> implements LoanTermsRepository {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public LoanTermsRepositoryImpl(EntityManager entityManager) {
        super(JpaEntityInformationSupport.getEntityInformation(LoanTerms.class, entityManager), entityManager);
    }

    @Override
    public LoanTerms getCurrentLoanTerms() {
        return findOne((root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.isTrue(root.get(LoanTerms_.current))).orElse(null);
    }
}
