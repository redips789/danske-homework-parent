package danske.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

@AllArgsConstructor
@Getter
public class CreditApplicationView implements Serializable {
    private static final long serialVersionUID = 1028412372629944493L;

    private Long customerId;
    private double amount;
    private int term;
    private double montlyPayment;
}
