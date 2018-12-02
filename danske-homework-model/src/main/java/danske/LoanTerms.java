package danske;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "loan_terms")
@Setter
@Getter
public class LoanTerms {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "annual_interest_rate", nullable = false)
    private double annualInterestRate;

    @Column(name = "minimum_loan_term", nullable = false)
    private int minimumLoanTerm;

    @Column(name = "maximum_loan_term", nullable = false)
    private int maximumLoanTerm;

    @Column(name = "is_current", nullable = false)
    private boolean current;
}
