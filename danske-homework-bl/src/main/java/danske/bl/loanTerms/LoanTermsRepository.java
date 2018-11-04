package danske.bl.loanTerms;

import danske.LoanTerms;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanTermsRepository extends JpaRepository<LoanTerms, Long> {

    LoanTerms getCurrentLoanTerms();
}
