package med.voll.api.address;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address {

    private String logradouro;
    private String bairro;
    private String cep;
    private String numero;
    private String complemento;
    private String cidade;
    private String uf;

    public Address(AddressData addressData){
        this.logradouro = addressData.logradouro();
        this.bairro = addressData.bairro();
        this.cep = addressData.cep();
        this.numero = addressData.numero();
        this.complemento = addressData.complemento();
        this.cidade = addressData.cidade();
        this.uf = addressData.uf();
    }

}
