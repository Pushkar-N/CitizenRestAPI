package com.numinolabs.PNCitizenAPI.Controller;

import com.numinolabs.PNCitizenAPI.Model.Citizen;
import com.numinolabs.PNCitizenAPI.Repo.CitizenRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "users")
public class ApiController {
    @Autowired
    private CitizenRepo citizenRepo;

    @GetMapping(path = "")
    public List<Citizen> getCitizen(){
        return citizenRepo.findAll();
    }

    @GetMapping(path = "/{id}",produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    public ResponseEntity<Citizen> getUser(@PathVariable long id){
        Citizen citizen = citizenRepo.findById(id).get();

        return new ResponseEntity<Citizen>(citizen, HttpStatus.OK);
    }

//    @PostMapping
//    public String createCitizen(@RequestBody Citizen citizen){
//        citizenRepo.save(citizen);
//        return "Saved";
//    }

    @PostMapping
    public String createCitizens(@RequestBody List<Citizen> citizen){
        for (Citizen c: citizen
             ) {
            citizenRepo.save(c);
        }
        return "saved list of users...";
    }

    @PutMapping(path = "/{id}")
    public String updateCitizen(@PathVariable(value = "id") Long id,@RequestBody Citizen citizen){
        Citizen UpdatedCitizen = citizenRepo.findById(id).get();
        UpdatedCitizen.setFullName(citizen.getFullName());
        UpdatedCitizen.setAadharId(citizen.getAadharId());
        UpdatedCitizen.setDateOfBirth(citizen.getDateOfBirth());
        UpdatedCitizen.setGender(citizen.getGender());
        UpdatedCitizen.setPincode(citizen.getPincode());
        UpdatedCitizen.setState(citizen.getState());
        citizenRepo.save(UpdatedCitizen);
        return "Updated..";
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteCitizen(@PathVariable Long id){
        Citizen citizen = citizenRepo.findById(id).get();
        citizenRepo.delete(citizen);
        return ResponseEntity.noContent().build();
//        return "Deleted user with id: "+ id.toString();
    }
}
