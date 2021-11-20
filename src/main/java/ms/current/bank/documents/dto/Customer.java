package ms.current.bank.documents.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Customer {

    @Id
    private String id;

    private String customerIdentityType;

    private String customerIdentityNumber;

    private String name;

    private String email;

    private String phone;

    private String address;

    private CustomerType customerType;

    private String dateOperation;
}
