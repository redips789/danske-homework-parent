package danske;

import danske.bl.creditApplication.CreditApplicationRepository;
import danske.bl.customer.CustomerRepository;
import danske.bl.loanTerms.LoanTermsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

    @RequestMapping(value = "/creditApplication/create", method = RequestMethod.POST)
    public CreditApplication createCreditApplication(@RequestBody CreditApplication creditApplication) {
        creditApplicationRepository.save(creditApplication);
        //return new CreditApplicationView(customerId, amount, term, creditApplication.getMonthlyPayments());
        return null;
    }

    @RequestMapping(value = "/customer/{customerId}/allCreditApplications", method = RequestMethod.GET)
    public List<CreditApplication> getCustomerCreditApplications(@PathVariable(value = "customerId") Long customerId) {
        return creditApplicationRepository.getCreditApplicationsByCustomerId(customerId);
    }
}
