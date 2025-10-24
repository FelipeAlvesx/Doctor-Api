package med.voll.api.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import med.voll.api.domain.patients.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("patients")
@SecurityRequirement(name = "bearer-key")
public class patientsController {

    @Autowired
    private PatientsRepository patientsRepository;

    @PostMapping
    @Transactional
    public ResponseEntity login(@RequestBody @Valid PatientsLoginData patientsLoginData, UriComponentsBuilder uriComponentsBuilder){
        var patient = new Patients(patientsLoginData);
        patientsRepository.save(patient);

        var uri = uriComponentsBuilder.path("/patients/{id}").buildAndExpand(patient.getId()).toUri();

        return ResponseEntity.created(uri).body(new PatientsDetailResponse(patient));

    }


    @GetMapping
    public ResponseEntity<Page<PatientsResponseData>> readAll(@PageableDefault(size = 10, sort = {"name"}) Pageable pageable){
        var pageResponse = patientsRepository.findAllByActiveTrue(pageable).map(PatientsResponseData::new);

        return ResponseEntity.ok(pageResponse);
    }

    @PutMapping
    @Transactional
    public ResponseEntity update(@RequestBody @Valid PatientsUpdateData patientsUpdateData){
        var patient = patientsRepository.getReferenceById(patientsUpdateData.id());
        patient.updatePatient(patientsUpdateData);

        return ResponseEntity.ok(new PatientsDetailResponse(patient));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity delete(@PathVariable Long id){
        var patient = patientsRepository.getReferenceById(id);
        patient.delete();

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity readOne(@PathVariable Long id){
        var patient = patientsRepository.getReferenceById(id);

        return ResponseEntity.ok(new PatientsDetailResponse(patient));
    }

}
