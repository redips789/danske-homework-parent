package danske.bl.loanTerms;

import danske.model.LoanTerms;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanTermsRepository extends JpaRepository<LoanTerms, Long> {

    LoanTerms getCurrentLoanTerms();

}
