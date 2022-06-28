package com.numinolabs.PNCitizenAPI.Controller;

import com.numinolabs.PNCitizenAPI.Model.CitizenEducation;
import com.numinolabs.PNCitizenAPI.Repository.CitizenEducationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(path = "citizenEducation")
public class CitizenEducationController {

    @Autowired
    CitizenEducationRepository citizenEducationRepo;

    @GetMapping(path = "")
    public List<CitizenEducation> getallCitizenEducation(){
        return citizenEducationRepo.findAll();
    }

    @GetMapping(path = "/{citizen_id}")
    public ResponseEntity<CitizenEducation> getCitizenEducationDetails(@PathVariable(value = "citizen_id") Long id){
        CitizenEducation citizenEducation = citizenEducationRepo.findByCitizenId(id);
        if(citizenEducation == null)
            return ResponseEntity.noContent().build();
        else
            return new ResponseEntity<>(citizenEducation,HttpStatus.OK);
    }

    @PostMapping(path = "")
    public ResponseEntity<Object> createCitizenEducation(@RequestBody CitizenEducation citizenEducation){
        citizenEducationRepo.save(citizenEducation);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{citizen_id}")
                        .buildAndExpand(citizenEducation.getCitizenId()).toUri();
        return ResponseEntity.created(location).build();
    }


    @DeleteMapping(path = "/{citizen_id}" )
    public void deleteCitizenEducation(@PathVariable(value = "citizen_id") long id){
        citizenEducationRepo.deleteByCitizenId(id);
    }

    @PutMapping(path = "/{citizen_id}")
    public ResponseEntity<Object> updateCitizenEducation(@RequestBody CitizenEducation citizenEducation, @PathVariable(value = "citizen_id") long id ){
        CitizenEducation currentCitizenEducation =  citizenEducationRepo.findByCitizenId(id);
        if(currentCitizenEducation == null)
            return ResponseEntity.notFound().build();

        citizenEducation.setCitizenId(id);
        citizenEducationRepo.save(citizenEducation);
        return ResponseEntity.noContent().build();
    }


}
