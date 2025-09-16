package med.voll.api.doctor;

import med.voll.api.address.Address;

public record DoctorDetailResponse(Long id, String name, String email, String phone, String crm, Espec espec, Address address) {

    public DoctorDetailResponse(Doctor doctor){
        this(doctor.getId(), doctor.getName(), doctor.getEmail(), doctor.getPhone(), doctor.getCrm(), doctor.getEspec(), doctor.getAddress());
    }

}
