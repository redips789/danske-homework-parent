package danske;

import org.apache.commons.lang3.StringUtils;

public class ValidationUtils {

    public static void validateCustomer(Customer customer) {
        if (StringUtils.isBlank(customer.getFirstName())) {
            throw new IllegalArgumentException("First name is required");
        }

        if (customer.getFirstName().length() > 255) {
            throw new IllegalArgumentException("First name is too long. First name should be no more than 255 symbols long");
        }

        if (StringUtils.isBlank(customer.getLastName())) {
            throw new IllegalArgumentException("Last name is required");
        }

        if (customer.getLastName().length() > 255) {
            throw new IllegalArgumentException("Last name is too long. Last name should be no more than 255 symbols long");
        }

        if (StringUtils.isBlank(customer.getAddress())) {
            throw new IllegalArgumentException("Address is required");
        }

        if (customer.getAddress().length() > 255) {
            throw new IllegalArgumentException("Address is too long. Last name should be no more than 255 symbols long");
        }

        if (StringUtils.isBlank(customer.getEmail())) {
            throw new IllegalArgumentException("Email is required");
        }

        if (customer.getEmail().length() < 255) {
            throw new IllegalArgumentException("Email is too long. Email should be no more than 255 symbols long");
        }

        if (customer.getMonthlyIncome() < 0) {
            throw new IllegalArgumentException("Monthly income is required and should be positive");
        }

        if (customer.getPhoneNumber() == null || customer.getPhoneNumber().length() > 20) {
            throw new IllegalArgumentException("Phone number is required and should be no more than 20 symbols long");
        }

        if (customer.getSocialSecurityNumber() == null || customer.getSocialSecurityNumber().length() > 20) {
            throw new IllegalArgumentException("Social security number is required and should be no more than 20 symbols long");
        }
    }

    public static void validateCreditApplication(CreditApplication creditApplication) {
        if (creditApplication.getRequestedAmount() < 0) {
            throw new IllegalArgumentException("Requested amount should be positive");
        }

        if (creditApplication.getCustomer() == null) {
            throw new IllegalArgumentException("Customer not found");
        }

        if (creditApplication.getLoanTerm() < 0) {
            throw  new IllegalArgumentException("Loan term in months should be positive");
        }

    }

    public static void validateLoanTerms(LoanTerms loanTerms) {
        if (loanTerms.getAnnualInterestRate() < 0) {
            throw new IllegalArgumentException("Annual interest rate must be positive");
        }

        if (loanTerms.getMinimumLoanTerm() <= 0) {
            throw new IllegalArgumentException("Minimum loan term must be positive");
        }

        if (loanTerms.getMaximumLoanTerm() <= 0) {
            throw new IllegalArgumentException("Maximum loan term must be positive");
        }

        if (loanTerms.getMinimumLoanTerm() > loanTerms.getMaximumLoanTerm()) {
            throw new IllegalArgumentException("Maximum loan term must be greater or equal than minimum loan term");
        }
    }
}
