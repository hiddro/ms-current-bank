package ms.current.bank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class MsCurrentBankApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsCurrentBankApplication.class, args);
	}

}
