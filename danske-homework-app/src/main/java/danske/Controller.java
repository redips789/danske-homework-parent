package danske;

import danske.bl.creditApplication.CreditApplicationRepository;
import danske.bl.customer.CustomerRepository;
import danske.bl.loanTerms.LoanTermsRepository;
import danske.model.CustomerWithTotalCreditAmountView;
import danske.view.CreditApplicationView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class Controller {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private LoanTermsRepository loanTermsRepository;

    @Autowired
    private CreditApplicationRepository creditApplicationRepository;

    @RequestMapping("/createNewCustomer")
    public Long createNewCustomer(@RequestParam(value = "firstName") String firstName,
                                  @RequestParam(value = "lastName") String lastName,
                                  @RequestParam(value = "address") String address,
                                  @RequestParam(value = "number") String socialSecurityNumber,
                                  @RequestParam(value = "email") String email,
                                  @RequestParam(value = "phone") String phoneNumber,
                                  @RequestParam(value = "income") Long monthlyIncome) {
        Customer customer = new Customer();
        customer.setFirstName(firstName);
        customer.setLastName(lastName);
        customer.setAddress(address);
        customer.setSocialSecurityNumber(socialSecurityNumber);
        customer.setEmail(email);
        customer.setPhoneNumber(phoneNumber);
        customer.setMonthlyIncome(monthlyIncome);
        ValidationUtils.validateCustomer(customer);
        customer = customerRepository.save(customer);
        return customer.getId();
    }

    @RequestMapping("/createCreditApplication")
    public CreditApplicationView createCreditApplication(@RequestParam(value = "customerId") Long customerId,
                                                     @RequestParam(value = "amount") double amount,
                                                     @RequestParam(value = "term") int term) {
        CreditApplication creditApplication = new CreditApplication();
        creditApplication.setLoanTerm(term);
        creditApplication.setCustomer(customerRepository.findById(customerId).orElse(null));
        creditApplication.setGlobalLoanTerms(loanTermsRepository.getCurrentLoanTerms());
        creditApplication.setRequestedAmount(amount);
        ValidationUtils.validateCreditApplication(creditApplication);
        creditApplicationRepository.save(creditApplication);
        return new CreditApplicationView(customerId, amount, term, creditApplication.getMonthlyPayments());
    }

    @RequestMapping("/getCustomerCreditApplications")
    public List<CreditApplication> getCustomerCreditApplications(@RequestParam(value = "customerId") Long customerId) {
        return creditApplicationRepository.getCreditApplicationsByCustomerId(customerId);
    }

    @RequestMapping("/getCurrentLoanTerms")
    public LoanTerms getCurrentLoanTerms() {
        return loanTermsRepository.getCurrentLoanTerms();
    }


    //TODO create DB trigger or implement pessimistic lock. There should be only one current LoanTerms
    @RequestMapping("/updateLoanTerms")
    public LoanTerms updateLoanTerms(@RequestParam(value = "interest") Double annualInterestRate,
                                     @RequestParam(value = "min") int minLoanTerm,
                                     @RequestParam(value = "max") int maxLoanTerm) {
        LoanTerms loanTerms = new LoanTerms();
        loanTerms.setAnnualInterestRate(annualInterestRate);
        loanTerms.setCurrent(true);
        loanTerms.setMinimumLoanTerm(minLoanTerm);
        loanTerms.setMaximumLoanTerm(maxLoanTerm);
        ValidationUtils.validateLoanTerms(loanTerms);
        LoanTerms previousLoanTerms = loanTermsRepository.getCurrentLoanTerms();
        loanTerms = loanTermsRepository.save(loanTerms);
        previousLoanTerms.setCurrent(false);
        loanTermsRepository.save(previousLoanTerms);
        return loanTerms;
    }

    @RequestMapping("/getCustomersCreditApplications")
    public List<CustomerWithTotalCreditAmountView> getAllCustomersWithCreditApplicationAmountsSummedUp() {
        return customerRepository.getCustomersCreditApplicationsAmountsSummedUp();
    }
}