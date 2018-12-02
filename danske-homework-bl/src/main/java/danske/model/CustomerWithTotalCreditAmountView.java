package danske.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CustomerWithTotalCreditAmountView {
    private String firstName;
    private String lastName;
    private String socialSecurityNumber;
    private double totalAmount;
}
