package danske;

import danske.bl.customer.CustomerRepository;
import danske.model.CustomerWithTotalCreditAmountView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

    @RequestMapping(value = "/customer/create", method = RequestMethod.POST)
    public Long createCustomer(@RequestBody Customer customer) {
        customer = customerRepository.save(customer);
        return customer.getId();
    }

    @RequestMapping(value = "/customers/totalamount", method = RequestMethod.GET)
    public List<CustomerWithTotalCreditAmountView> getAllCustomersWithCreditApplicationAmountsSummedUp() {
        return customerRepository.getCustomersCreditApplicationsAmountsSummedUp();
    }
}