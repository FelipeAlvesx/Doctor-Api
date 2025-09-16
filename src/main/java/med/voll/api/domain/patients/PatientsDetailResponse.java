package med.voll.api.domain.patients;

import med.voll.api.domain.address.Address;

public record PatientsDetailResponse(Long id, String name, String email,String cpf, String phone, Address address) {

    public PatientsDetailResponse(Patients patients){
        this(patients.getId(), patients.getName(), patients.getEmail(), patients.getCpf(), patients.getPhone(), patients.getAddress());
    }
}
