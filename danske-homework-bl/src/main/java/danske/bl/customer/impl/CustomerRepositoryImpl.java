package danske.bl.customer.impl;

import danske.CreditApplication;
import danske.CreditApplication_;
import danske.Customer;
import danske.Customer_;
import danske.bl.customer.CustomerRepository;
import danske.model.CustomerWithTotalCreditAmountView;
import org.springframework.data.jpa.repository.support.JpaEntityInformationSupport;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Tuple;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Component("customerRepositoryImpl")
public class CustomerRepositoryImpl extends SimpleJpaRepository<Customer, Long> implements CustomerRepository {

    @PersistenceContext
    private EntityManager em;

    public CustomerRepositoryImpl(EntityManager entityManager) {
        super(JpaEntityInformationSupport.getEntityInformation(Customer.class, entityManager), entityManager);
    }

    @Override
    public List<CustomerWithTotalCreditAmountView> getCustomersCreditApplicationsAmountsSummedUp() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Tuple> criteriaQuery = cb.createTupleQuery();
        Root<Customer> root = criteriaQuery.from(Customer.class);

        Join<Customer, CreditApplication> creditApplicationJoin = root.join(Customer_.creditApplications, JoinType.LEFT);

        Path<String> firstName = root.get(Customer_.firstName);
        Path<String> lastName = root.get(Customer_.lastName);
        Path<String> socialSecurityNumber = root.get(Customer_.socialSecurityNumber);
        Expression<Double> totalAmount = cb.sum(creditApplicationJoin.get(CreditApplication_.requestedAmount));

        criteriaQuery.multiselect(firstName, lastName, socialSecurityNumber, totalAmount);
        criteriaQuery.groupBy(firstName, lastName, socialSecurityNumber);
        criteriaQuery.orderBy(cb.desc(cb.sum(creditApplicationJoin.get(CreditApplication_.requestedAmount))));

        return em.createQuery(criteriaQuery).getResultList().stream().map(t -> new CustomerWithTotalCreditAmountView(t.get(firstName), t.get(lastName), t.get(socialSecurityNumber),
                t.get(totalAmount))).collect(Collectors.toList());
    }
}
