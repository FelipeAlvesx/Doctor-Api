package med.voll.api.doctor;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import med.voll.api.address.Address;

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

    public Doctor(LoginData loginData){
        this.name = loginData.name();
        this.email = loginData.email();
        this.phone = loginData.phone();
        this.crm = loginData.crm();
        this.espec = loginData.espec();
        this.address = new Address(loginData.addressData());

    }

}
