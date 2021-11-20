package ms.current.bank.repository;

import ms.current.bank.documents.entities.CurrentAccount;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface CurrentAccountRepository extends ReactiveMongoRepository<CurrentAccount, String> {

    Mono<CurrentAccount> findByCustomerIdentityNumber(String customerIdentityNumber);

    Mono<CurrentAccount> findByAccountNumber(String accountNumber);
}
