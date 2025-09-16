package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.domain.doctor.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("doctor")
public class doctorController {

    @Autowired
    private DoctorRepository doctorRepository;

    /* Create */
    @PostMapping
    @Transactional
    public ResponseEntity<DoctorDetailResponse> create(@RequestBody @Valid DoctorLoginData data, UriComponentsBuilder uriComponentsBuilder){
        var doctor = new Doctor(data);
        doctorRepository.save(doctor);

        var uri = uriComponentsBuilder.path("/doctor/{id}").buildAndExpand(doctor.getId()).toUri();

        return ResponseEntity.created(uri).body(new DoctorDetailResponse(doctor));
    }

    /* Read */
    @GetMapping
    public ResponseEntity<Page<DoctorResponseData>> readAll(@PageableDefault(size = 10, sort = {"name"}) Pageable pageable){
        var pageResponse = doctorRepository.findAllByActiveTrue(pageable).map(DoctorResponseData::new);

        return ResponseEntity.ok(pageResponse);

    }

    /* Update */
    @PutMapping
    @Transactional
    public ResponseEntity<DoctorDetailResponse> update(@RequestBody @Valid DoctorUpdateData doctorUpdateData){
        var doctor = doctorRepository.getReferenceById(doctorUpdateData.id());
        doctor.updateDoctor(doctorUpdateData);

        return ResponseEntity.ok(new DoctorDetailResponse(doctor));
    }

    /* Delete */
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity delete(@PathVariable Long id){
        var doctor = doctorRepository.getReferenceById(id);
        doctor.delete();

        return ResponseEntity.noContent().build();
    }


    @GetMapping("/{id}")
    public ResponseEntity<DoctorDetailResponse> readOne(@PathVariable Long id){
        var doctor = doctorRepository.getReferenceById(id);

        return ResponseEntity.ok(new DoctorDetailResponse(doctor));
    }


}
