package ms.current.bank.service;

import ms.current.bank.documents.dto.Customer;
import reactor.core.publisher.Mono;

public interface ICustomerService {
    Mono<Customer> getCustomer(String customerIdentityNumber);
}
