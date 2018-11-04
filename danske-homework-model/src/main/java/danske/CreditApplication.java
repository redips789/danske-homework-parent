package danske;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "credit_application")
public class CreditApplication {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @Column(name = "requested_amount")
    private double requestedAmount;

    @Column(name = "loan_term")
    private int loanTerm;

    @ManyToOne
    @JoinColumn(name = "global_loan_terms_id")
    private LoanTerms globalLoanTerms;

    @Transient
    public double getMonthlyPayments() {
        double annualInterestRate = globalLoanTerms.getAnnualInterestRate();
        return ((annualInterestRate / 12) * requestedAmount) / (1 - Math.pow((1 + annualInterestRate / 12), loanTerm));
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public double getRequestedAmount() {
        return requestedAmount;
    }

    public void setRequestedAmount(double requestedAmount) {
        this.requestedAmount = requestedAmount;
    }

    public int getLoanTerm() {
        return loanTerm;
    }

    public void setLoanTerm(int loanTerm) {
        this.loanTerm = loanTerm;
    }

    public LoanTerms getGlobalLoanTerms() {
        return globalLoanTerms;
    }

    public void setGlobalLoanTerms(LoanTerms globalLoanTerms) {
        this.globalLoanTerms = globalLoanTerms;
    }
}
