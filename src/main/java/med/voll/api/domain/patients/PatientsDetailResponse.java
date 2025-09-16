package med.voll.api.patients;

import med.voll.api.address.Address;
import med.voll.api.doctor.Espec;

public record PatientsDetailResponse(Long id, String name, String email,String cpf, String phone, Address address) {

    public PatientsDetailResponse(Patients patients){
        this(patients.getId(), patients.getName(), patients.getEmail(), patients.getCpf(), patients.getPhone(), patients.getAddress());
    }
}
