package ms.current.bank.service;

import ms.current.bank.documents.entities.CurrentAccount;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ICurrentService extends ICrudService<CurrentAccount, String>{

    Mono<CurrentAccount> saveCurrentAccount(CurrentAccount currentAccount);

    Flux<CurrentAccount> getAllFixedTerm();

    Mono<CurrentAccount> getByIdCustomer(String id);

    Mono<CurrentAccount> getByIdNumber(String id);

    Mono<CurrentAccount> updateCurrentAccount (String id, CurrentAccount currentAccount);

    Mono<Void> deleteCurrentAccount (String id);

}
