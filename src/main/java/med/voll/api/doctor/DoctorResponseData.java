package med.voll.api.doctor;

public record DoctorResponseData(Long id, String name, String email, String crm, Espec espec) {

   public DoctorResponseData(Doctor doctor){
        this(doctor.getId(), doctor.getName(),doctor.getEmail(), doctor.getCrm(), doctor.getEspec());
    }

}