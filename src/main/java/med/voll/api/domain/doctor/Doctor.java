package med.voll.api.doctor;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import med.voll.api.address.Address;
import med.voll.api.address.AddressData;

@Table(name = "doctors")
@Entity(name = "Doctor")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String phone;
    private String crm;

    @Enumerated(EnumType.STRING)
    private Espec espec;

    @Embedded
    private Address address;

    private Boolean active;

    public Doctor(DoctorLoginData doctorLoginData){
        this.active = true;
        this.name = doctorLoginData.name();
        this.email = doctorLoginData.email();
        this.phone = doctorLoginData.phone();
        this.crm = doctorLoginData.crm();
        this.espec = doctorLoginData.espec();
        this.address = new Address(doctorLoginData.addressData());

    }

    public void updateDoctor(DoctorUpdateData doctorUpdateData){
        if(doctorUpdateData.name() != null){
            this.name = doctorUpdateData.name();
        }
        if(doctorUpdateData.phone() != null){
            this.phone = doctorUpdateData.phone();
        }
        if (doctorUpdateData.addressData() != null){
            this.address.updateAddress(doctorUpdateData.addressData());
        }

    }

    public void delete() {
        this.active = false;
    }
}
