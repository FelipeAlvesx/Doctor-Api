package med.voll.api.patients;

import jakarta.validation.constraints.NotNull;
import med.voll.api.address.AddressData;

public record PatientsUpdateData(

        @NotNull
        Long id,
        String name,
        String email,
        String phone,
        AddressData addressData
) {
}
