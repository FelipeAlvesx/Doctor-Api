package med.voll.api.doctor;

import med.voll.api.address.AddressData;

public record DataLogin(String name, String email, String crm, Espec espec, AddressData addressData) {
}
