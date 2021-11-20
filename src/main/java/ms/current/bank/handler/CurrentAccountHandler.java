package ms.current.bank.handler;

import ms.current.bank.documents.entities.CurrentAccount;
import ms.current.bank.service.ICurrentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class CurrentAccountHandler {

    @Autowired
    private ICurrentService currentService;

    public Mono<ServerResponse> findAll(ServerRequest request){
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(currentService.getAllFixedTerm(), CurrentAccount.class)
                .switchIfEmpty(ServerResponse.badRequest().build());
    }

    public Mono<ServerResponse> findByCustomerIdentityNumber(ServerRequest request){

        String id = request.pathVariable("customerIdentityNumber");

        return currentService.getByIdCustomer(id)
                .flatMap(c -> ServerResponse
                        .ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromValue(c)))
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> findByAccountNumber(ServerRequest request){

        String id = request.pathVariable("accountNumber");

        return currentService.getByIdNumber(id)
                .flatMap(c -> ServerResponse
                        .ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromValue(c)))
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> newCurrentAccount(ServerRequest request){

        Mono<CurrentAccount> currentAccountMono = request.bodyToMono(CurrentAccount.class);

        return currentAccountMono.flatMap(current -> currentService.saveCurrentAccount(current))
                .flatMap(c -> ServerResponse
                        .ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromValue(c)))
                .switchIfEmpty(ServerResponse.badRequest().build());
    }

    public Mono<ServerResponse> updateCurrentAccount(ServerRequest request){

        Mono<CurrentAccount> currentAccountMono = request.bodyToMono(CurrentAccount.class);

        String id = request.pathVariable("id");

        return currentAccountMono.flatMap(current -> currentService.updateCurrentAccount(id, current))
                .flatMap(c -> ServerResponse
                        .ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromValue(c)))
                .switchIfEmpty(ServerResponse.badRequest().build());
    }

    public Mono<ServerResponse> deleteCurrentAccount(ServerRequest request){

        String id = request.pathVariable("id");

        Mono<CurrentAccount> currentAccountMono = currentService.findById(id);

        return currentAccountMono.flatMap(current -> currentService.deleteCurrentAccount(current.getId()))
                .flatMap(c -> ServerResponse
                        .ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromValue(c)))
                .switchIfEmpty(ServerResponse.badRequest().build());
    }

}
