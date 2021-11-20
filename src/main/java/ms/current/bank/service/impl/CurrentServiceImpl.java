package ms.current.bank.service.impl;

import ms.current.bank.documents.dto.CustomerDTO;
import ms.current.bank.documents.entities.CurrentAccount;
import ms.current.bank.repository.CurrentAccountRepository;
import ms.current.bank.service.ICurrentService;
import ms.current.bank.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CurrentServiceImpl implements ICurrentService {

    @Autowired
    private CurrentAccountRepository currentAccountRepository;

    @Autowired
    private ICustomerService customerService;

    @Override
    public Mono<CurrentAccount> create(CurrentAccount o) {
        return currentAccountRepository.save(o);
    }

    @Override
    public Flux<CurrentAccount> findAll() {
        return currentAccountRepository.findAll();
    }

    @Override
    public Mono<CurrentAccount> findById(String s) {
        return currentAccountRepository.findById(s);
    }

    @Override
    public Mono<CurrentAccount> update(CurrentAccount o) {
        return currentAccountRepository.save(o);
    }

    @Override
    public Mono<Void> delete(CurrentAccount o) {
        return currentAccountRepository.delete(o);
    }

    @Override
    public Mono<CurrentAccount> saveCurrentAccount(CurrentAccount currentAccount) {
        return customerService.getCustomer(currentAccount.getCustomerIdentityNumber()).flatMap(c -> {
            if(c.getId() == null || currentAccount.getTitulares().size() == 0){
                return Mono.empty();
            }

            currentAccount.setTypeOfAccount("CURRENT_ACCOUNT");
            currentAccount.setAmount(0.0);
            currentAccount.setCustomer(CustomerDTO.builder().name(c.getName())
                    .code(c.getCustomerType().getCode())
                    .customerIdentityNumber(c.getCustomerIdentityNumber()).build());

            return currentAccountRepository.save(currentAccount);
        });
    }

    @Override
    public Flux<CurrentAccount> getAllFixedTerm() {
        return currentAccountRepository.findAll();
    }

    @Override
    public Mono<CurrentAccount> getByIdCustomer(String id) {
        return currentAccountRepository.findByCustomerIdentityNumber(id);
    }

    @Override
    public Mono<CurrentAccount> getByIdNumber(String id) {
        return currentAccountRepository.findByAccountNumber(id);
    }

    @Override
    public Mono<CurrentAccount> updateCurrentAccount(String id, CurrentAccount currentAccount) {
        Mono<CurrentAccount> currentAccountMono = currentAccountRepository.findById(id);
        return currentAccountMono.flatMap(currentAcc -> {
            currentAcc.setTypeOfAccount(currentAccount.getTypeOfAccount());
            currentAcc.setCustomerIdentityNumber(currentAccount.getCustomerIdentityNumber());
            currentAcc.setAccountNumber(currentAccount.getAccountNumber());
            currentAcc.setAmount(currentAccount.getAmount());
            return currentAccountRepository.save(currentAcc);
        }).switchIfEmpty(Mono.empty());
    }

    @Override
    public Mono<Void> deleteCurrentAccount(String id) {
        return currentAccountRepository.deleteById(id);
    }
}
