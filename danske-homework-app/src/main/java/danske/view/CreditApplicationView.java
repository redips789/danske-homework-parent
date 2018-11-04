package danske.view;

public class CreditApplicationView {
    private Long customerId;
    private double amount;
    private int term;
    private double montlyPayment;

    public CreditApplicationView(Long customerId, double amount, int term, double montlyPayment) {
        this.customerId = customerId;
        this.amount = amount;
        this.term = term;
        this.montlyPayment = montlyPayment;
    }
}
