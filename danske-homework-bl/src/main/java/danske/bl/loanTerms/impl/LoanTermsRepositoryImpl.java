package danske.bl.loanTerms.impl;

import danske.LoanTerms;
import danske.LoanTerms_;
import danske.bl.loanTerms.LoanTermsRepository;
import org.springframework.data.jpa.repository.support.JpaEntityInformationSupport;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@Transactional
@Component("loanTermsRepositoryImpl")
public class LoanTermsRepositoryImpl extends SimpleJpaRepository<LoanTerms, Long> implements LoanTermsRepository {

    public LoanTermsRepositoryImpl(EntityManager entityManager) {
        super(JpaEntityInformationSupport.getEntityInformation(LoanTerms.class, entityManager), entityManager);
    }

    @Override
    public LoanTerms getCurrentLoanTerms() {
        return findOne((root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.isTrue(root.get(LoanTerms_.current))).orElse(null);
    }
}
