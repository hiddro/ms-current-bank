package ms.current.bank.documents.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import ms.current.bank.documents.dto.CustomerDTO;
import ms.current.bank.documents.dto.Firmante;
import ms.current.bank.documents.dto.Titular;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.*;

@Document(collection = "current")
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CurrentAccount {

    @Id
    private String id;

    @Field(name = "typeOfAccount")
    private String typeOfAccount;

    @Field(name = "customerIdentityNumber")
    private String customerIdentityNumber;

    @Field(name = "accountNumber")
    @Indexed(unique=true)
    private String accountNumber;

    @Field(name = "amount")
    private double amount;

    @Field(name = "create_current")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date createDate = new Date();

    @Field(name = "titulares")
    private List<Titular> titulares= new ArrayList<>();

    @Field(name = "firmantes")
    private List<Firmante> firmantes= new ArrayList<>();

    @Field(name = "customer")
    private CustomerDTO customer;
}
