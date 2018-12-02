package danske.model;

import lombok.Getter;
import lombok.Setter;

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
@Getter
@Setter
public class CreditApplication {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @Column(name = "requested_amount", nullable = false)
    private double requestedAmount;

    @Column(name = "loan_term", nullable = false)
    private int loanTerm;

    @ManyToOne
    @JoinColumn(name = "global_loan_terms_id", nullable = false)
    private LoanTerms globalLoanTerms;

    @Transient
    public double getMonthlyPayments() {
        double annualInterestRate = globalLoanTerms.getAnnualInterestRate();
        return ((annualInterestRate / 12) * requestedAmount) / (1 - Math.pow((1 + annualInterestRate / 12), loanTerm));
    }
}
