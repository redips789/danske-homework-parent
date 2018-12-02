package danske;

import danske.bl.loanTerms.LoanTermsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoanTermsController {

    @Autowired
    private LoanTermsRepository loanTermsRepository;

    @RequestMapping(value = "/loanterms/current", method = RequestMethod.GET)
    public LoanTerms getCurrentLoanTerms() {
        return loanTermsRepository.getCurrentLoanTerms();
    }


    //TODO create DB trigger or implement pessimistic lock. There should be only one current LoanTerms
    @RequestMapping(value = "/loanterms/update", method = RequestMethod.POST)
    public LoanTerms updateLoanTerms(@RequestBody LoanTerms loanTerms) {
        ValidationUtils.validateLoanTerms(loanTerms);
        loanTerms = loanTermsRepository.save(loanTerms);
        return loanTerms;
    }
}
