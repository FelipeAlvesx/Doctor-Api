package med.voll.api.domain.patients;

import jakarta.validation.constraints.NotNull;

public record PatientsResponseData(

        @NotNull
        Long id,
        String name,
        String email
) {

    public PatientsResponseData(Patients patients){
        this(patients.getId(), patients.getName(), patients.getEmail());
    }

}
