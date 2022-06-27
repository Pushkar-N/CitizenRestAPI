package com.numinolabs.PNCitizenAPI.Controller;

import com.numinolabs.PNCitizenAPI.Model.Citizen;
import com.numinolabs.PNCitizenAPI.Repository.CitizenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "users")
public class ApiController {

    @Autowired
    private CitizenRepository citizenRepo;

    @GetMapping(path = "")
    public List<Citizen> getCitizen(){
        return citizenRepo.findAll();
    }

    @GetMapping(path = "/{id}",produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    public ResponseEntity<Citizen> getUser(@PathVariable long id){
        Citizen citizen =  citizenRepo.findById(id).get();
        return new ResponseEntity<Citizen>(citizen, HttpStatus.OK);
    }

    @PostMapping(path = "")
    public ResponseEntity<Object> createCitizens(@RequestBody Citizen citizen){
        Citizen savedCitizen = citizenRepo.save(citizen);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedCitizen.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Object> updateCitizen(@PathVariable(value = "id") Long id,@RequestBody Citizen citizen){

        Optional<Citizen> studentOptional = citizenRepo.findById(id);
        if (!studentOptional.isPresent())
            return ResponseEntity.notFound().build();

        citizen.setId(id);
        citizenRepo.save(citizen);
        return ResponseEntity.noContent().build();

    }

    @DeleteMapping(path = "/{id}")
    public void deleteCitizen(@PathVariable(value = "id") Long id){
        citizenRepo.deleteById(id);
    }
}
