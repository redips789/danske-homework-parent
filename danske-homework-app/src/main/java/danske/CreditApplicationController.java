package danske;

import danske.bl.creditApplication.CreditApplicationRepository;
import danske.bl.customer.CustomerRepository;
import danske.bl.loanTerms.LoanTermsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CreditApplicationController {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private LoanTermsRepository loanTermsRepository;

    @Autowired
    private CreditApplicationRepository creditApplicationRepository;

    @RequestMapping("/createCreditApplication")
    public CreditApplication createCreditApplication(@RequestBody CreditApplication creditApplication) {
        creditApplicationRepository.save(creditApplication);
        //return new CreditApplicationView(customerId, amount, term, creditApplication.getMonthlyPayments());
        return null;
    }

    @RequestMapping("/getCustomerCreditApplications")
    public List<CreditApplication> getCustomerCreditApplications(@RequestParam(value = "customerId") Long customerId) {
        return creditApplicationRepository.getCreditApplicationsByCustomerId(customerId);
    }
}
