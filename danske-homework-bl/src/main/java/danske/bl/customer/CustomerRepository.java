package danske.bl.customer;

import danske.Customer;
import danske.model.CustomerWithTotalCreditAmountView;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    List<CustomerWithTotalCreditAmountView> getCustomersCreditApplicationsAmountsSummedUp();
}
