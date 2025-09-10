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

    public void updateAddress(AddressData addressData){
        if(addressData.logradouro() != null){
            this.logradouro = addressData.logradouro();
        }
        if(addressData.bairro() != null){
            this.bairro = addressData.bairro();
        }
        if (addressData.cep() != null){
            this.cep = addressData.cep();
        }
        if(addressData.numero() != null){
            this.numero = addressData.numero();
        }
        if(addressData.complemento() != null){
            this.complemento = addressData.complemento();
        }
        if(addressData.cidade() != null){
            this.cidade = addressData.cidade();
        }
        if(addressData.uf() != null){
            this.uf = addressData.uf();
        }
    }

}
