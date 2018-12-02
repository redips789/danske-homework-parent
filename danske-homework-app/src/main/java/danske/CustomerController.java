package danske;

import danske.bl.creditApplication.CreditApplicationRepository;
import danske.bl.customer.CustomerRepository;
import danske.bl.loanTerms.LoanTermsRepository;
import danske.model.CustomerWithTotalCreditAmountView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private LoanTermsRepository loanTermsRepository;

    @Autowired
    private CreditApplicationRepository creditApplicationRepository;

    @RequestMapping("/createNewCustomer")
    public Long createNewCustomer(@RequestBody Customer customer) {
        customer = customerRepository.save(customer);
        return customer.getId();
    }

    @RequestMapping("/getCustomersCreditApplications")
    public List<CustomerWithTotalCreditAmountView> getAllCustomersWithCreditApplicationAmountsSummedUp() {
        return customerRepository.getCustomersCreditApplicationsAmountsSummedUp();
    }
}