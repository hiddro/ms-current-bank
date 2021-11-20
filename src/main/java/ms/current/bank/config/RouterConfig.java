package ms.current.bank.config;

import ms.current.bank.handler.CurrentAccountHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class RouterConfig {

    @Bean
    public RouterFunction<ServerResponse> routes(CurrentAccountHandler currentAccountHandler){

        return route(GET("/getAll"), currentAccountHandler::findAll)
                .andRoute(GET("/getByIdCustomer/{customerIdentityNumber}"),currentAccountHandler::findByCustomerIdentityNumber)
                .andRoute(GET("/getByIdNumber/{accountNumber}"), currentAccountHandler::findByAccountNumber)
                .andRoute(POST("/create"), currentAccountHandler::newCurrentAccount)
                .andRoute(PUT("/update/{id}"), currentAccountHandler::updateCurrentAccount)
                .andRoute(DELETE("/delete/{id}"), currentAccountHandler::deleteCurrentAccount);
    }
}
