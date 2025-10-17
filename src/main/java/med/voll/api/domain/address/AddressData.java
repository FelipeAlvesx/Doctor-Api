package med.voll.api.domain.address;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record  AddressData(

        @NotBlank
        String logradouro,

        @NotBlank
        String bairro,

        @NotBlank
        @Pattern(regexp = "\\d{8}", message = "cep inválido")
        String cep,

        @NotBlank
        String cidade,

        @NotBlank
        String uf,

        String numero,
        String complemento) {
}
