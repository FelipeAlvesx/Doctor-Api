package med.voll.api.domain.patients;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import med.voll.api.domain.address.AddressData;

public record PatientsLoginData(

        @NotBlank
        String name,

        @NotBlank
        @Email
        String email,

        @NotBlank
        @Pattern(regexp = "\\d{3}\\.?\\d{3}\\.?\\d{3}\\-?\\d{2}")
        String cpf,

        @NotBlank
        String phone,

        @Valid
        AddressData addressData) {
}
