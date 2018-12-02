package danske.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

@AllArgsConstructor
@Getter
public class CustomerWithTotalCreditAmountView implements Serializable {
    private static final long serialVersionUID = -8397714337316328259L;

    private String firstName;
    private String lastName;
    private String socialSecurityNumber;
    private double totalAmount;
}
