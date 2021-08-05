package com.core.ecommanager.controller;

import com.core.ecommanager.model.Facility;
import com.core.ecommanager.model.User;
import com.core.ecommanager.repository.FacilityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/facility")
public class FacilityController {

    @Autowired
    private FacilityRepository facilityRepo;

    @GetMapping
    public List<Facility> findAllUsers(){
        return facilityRepo.findAll();
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Facility> findUserById(@PathVariable(value = "id") Long id){
        Optional<Facility> facility = facilityRepo.findById(id);

        if(facility.isPresent()){
            return ResponseEntity.ok().body(facility.get());
        } else{
            return ResponseEntity.notFound().build();
        }
    }
}
