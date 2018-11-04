package danske.model;

public class CustomerWithTotalCreditAmountView {

    private String firstName;
    private String lastName;
    private String socialSecurityNumber;
    private double totalAmount;

    public CustomerWithTotalCreditAmountView(String firstName, String lastName, String socialSecurityNumber, double totalAmount) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.socialSecurityNumber = socialSecurityNumber;
        this.totalAmount = totalAmount;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getSocialSecurityNumber() {
        return socialSecurityNumber;
    }

    public double getTotalAmount() {
        return totalAmount;
    }
}
