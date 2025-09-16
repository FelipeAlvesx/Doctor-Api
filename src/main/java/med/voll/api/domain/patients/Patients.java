package med.voll.api.domain.patients;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import med.voll.api.domain.address.Address;

@Entity
@Table(name = "patients")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
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

    private Boolean active;

    public Patients(PatientsLoginData patientsLoginData){
        this.active = true;
        this.name = patientsLoginData.name();
        this.email = patientsLoginData.email();
        this.cpf = patientsLoginData.cpf();
        this.phone = patientsLoginData.phone();
        this.address = new Address(patientsLoginData.addressData());
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
        if(patientsUpdateData.phone() != null){
            this.phone = patientsUpdateData.phone();
        }
    }

    public void delete(){
        this.active = false;
    }

}
