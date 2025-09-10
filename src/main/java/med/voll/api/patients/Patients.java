package med.voll.api.patients;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import med.voll.api.address.Address;
import med.voll.api.address.AddressData;

@Entity
@Table(name = "patients")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Patients {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String cpf;
    private String phone;

    @Embedded
    private Address address;

    public Patients(PatientsData patientsData){
        this.name = patientsData.name();
        this.email = patientsData.email();
        this.cpf = patientsData.cpf();
        this.phone = patientsData.phone();
        this.address = new Address(patientsData.addressData());
    }

    public void updatePatient(PatientsUpdateData patientsUpdateData){
        if (patientsUpdateData.name() != null){
            this.name = patientsUpdateData.name();
        }
        if(patientsUpdateData.email() != null){
            this.email = patientsUpdateData.email();
        }
        if(patientsUpdateData.addressData() != null){
            this.address.updateAddress(patientsUpdateData.addressData());
        }
    }

}
