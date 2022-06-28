package com.numinolabs.PNCitizenAPI.Controller;

import com.numinolabs.PNCitizenAPI.Model.Citizen;
import com.numinolabs.PNCitizenAPI.Repository.CitizenRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController(value = "Citizen Controller")
@RequestMapping(path = "citizen")
@Slf4j
public class ApiController {

    @Autowired
    private CitizenRepository citizenRepo;

    @Operation(summary = "Gets the list of all citizens")
    @ApiResponses(value = @ApiResponse(responseCode = "200"
                    , description = "Fetched the list of all Citizens"
                    , content = @Content(mediaType = "application/json"
                    , schema = @Schema(implementation = Citizen.class))))
    @GetMapping(path = "")
    public List<Citizen> getCitizen(){
        return citizenRepo.findAll();
    }

    @Operation(summary = "Request for citizen info")
    @ApiResponses(value = { @ApiResponse(responseCode = "200 OK"
                                            , description = "Successfully fetched Citizen info"
                                            , content ={ @Content(mediaType = "application/json", schema = @Schema(implementation = Citizen.class)) })
            ,@ApiResponse(responseCode = "404 Not Found", description = "Requested citizen was not found")
    })
    @GetMapping(path = "/{id}",produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    public ResponseEntity<Citizen> getCitizen(@Parameter(description = "Citizen Id") @PathVariable long id){
        log.info("Retrieving CITIZEN object from the repository for Id :"+id);
        Citizen citizen =  citizenRepo.findById(id).get();
        return new ResponseEntity<Citizen>(citizen, HttpStatus.OK);
    }

    @PostMapping(path = "")
    public ResponseEntity<Citizen> createCitizens(@Valid @RequestBody Citizen citizen){
        Citizen savedCitizen = citizenRepo.save(citizen);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedCitizen.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Citizen> updateCitizen(@PathVariable(value = "id") Long id,@RequestBody Citizen citizen){

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
