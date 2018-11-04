package danske;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "loan_terms")
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getAnnualInterestRate() {
        return annualInterestRate;
    }

    public void setAnnualInterestRate(double annualInterestRate) {
        this.annualInterestRate = annualInterestRate;
    }

    public int getMinimumLoanTerm() {
        return minimumLoanTerm;
    }

    public void setMinimumLoanTerm(int minimumLoanTerm) {
        this.minimumLoanTerm = minimumLoanTerm;
    }

    public int getMaximumLoanTerm() {
        return maximumLoanTerm;
    }

    public void setMaximumLoanTerm(int maximumLoanTerm) {
        this.maximumLoanTerm = maximumLoanTerm;
    }

    public boolean isCurrent() {
        return current;
    }

    public void setCurrent(boolean current) {
        this.current = current;
    }
}
