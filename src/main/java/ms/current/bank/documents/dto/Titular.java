package ms.current.bank.documents.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Getter
@Setter
public class Titular {

    @NotNull
    @NotBlank
    private String name;

    @NotNull
    @NotBlank
    private String dni;

}
