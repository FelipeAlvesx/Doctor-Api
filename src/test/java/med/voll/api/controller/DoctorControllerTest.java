package med.voll.api.controller;

import med.voll.api.domain.address.Address;
import med.voll.api.domain.address.AddressData;
import med.voll.api.domain.doctor.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@WithMockUser
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class DoctorControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<DoctorDetailResponse> jsonDoctorDetailResponse;

    @MockitoBean
    private DoctorRepository doctorRepository;

    @Test
    @DisplayName("test read one (doctor/id) returns doctor details when id exists")
    void readOne() throws Exception {

        var doctor = new Doctor(new DoctorLoginData(1L, "Dr. Disponivel", "doctor@gmail.com", "71997071909", "12345", Espec.CARDIOLOGY, dataAddress()));

        when(doctorRepository.getReferenceById(any()))
                .thenReturn(doctor);

        var response = mvc.perform(get("/doctor/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value()); // Placeholder assertion

        var expectedJson = jsonDoctorDetailResponse.write(
                new DoctorDetailResponse(doctor)
        ).getJson();

        assertThat(response.getContentAsString()).isEqualTo(expectedJson);
    }

    private AddressData dataAddress() {
        return new AddressData(
                "rua xpto",
                "bairro",
                "00000000",
                "Brasilia",
                "DF",
                null,
                null
        );
    }


}