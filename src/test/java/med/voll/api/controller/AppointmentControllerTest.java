package med.voll.api.controller;

import med.voll.api.domain.appointment.AppointmentService;
import med.voll.api.domain.appointment.DataCreateAppointment;
import med.voll.api.domain.appointment.DetailsAppointment;
import med.voll.api.domain.doctor.Espec;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser
@AutoConfigureJsonTesters
class AppointmentControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<DataCreateAppointment> jsonCreateAppointment;

    @Autowired
    private JacksonTester<DetailsAppointment> jsonDetailsAppointment;

    @MockitoBean
    private AppointmentService appointmentService;

    @Test
    @DisplayName("test http code 400 when request data is invalid")
    void create() throws Exception {
        var response = mvc.perform(post("/appointments"))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value()); // Placeholder assertion
    }

    @Test
    @DisplayName("test http code 200 when request data is valid")
    void create2() throws Exception {

        var now =  java.time.LocalDateTime.now().plusHours(12);
        var especialty = Espec.DERMATOLOGY;

        when(appointmentService.createAppointment(any(DataCreateAppointment.class)))
                .thenReturn(new DetailsAppointment(null, 1L, 1L, now, especialty));

        var response = mvc.perform(
                post("/appointments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonCreateAppointment.write(
                                new DataCreateAppointment(1L, 1L, now, especialty)).getJson()
                        ))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value()); // Placeholder assertion

        var expectedJson = jsonDetailsAppointment.write(
                new DetailsAppointment(null, 1L, 1L, now, especialty)
        ).getJson();

        assertThat(response.getContentAsString()).isEqualTo(expectedJson);
    }
}