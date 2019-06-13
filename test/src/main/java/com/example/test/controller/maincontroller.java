package com.example.test.controller;

import java.security.SecureRandom;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.management.relation.RelationNotFoundException;
import javax.validation.Valid;


import com.example.test.model.UserRepository;
import com.example.test.model.employee;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


 
@RestController
@RequestMapping("api/v1")

public class maincontroller {

    @Autowired
    private UserRepository userRepository;
         
    @GetMapping("/employee")
    public List<employee> getallemployee() {
        return userRepository.findAll();
    }

    @GetMapping("employee/{id}")
    public Optional<employee> getemployeebyid(@PathVariable(value = "id") Long employeeId)
            throws RelationNotFoundException {
        employee employe = userRepository.findById(employeeId)
                .orElseThrow(() -> new RelationNotFoundException("Employee not found on :: " + employeeId));
        return userRepository.findById(employeeId);

    }

    @PostMapping("/employee")
    public employee createEmployee(@Valid @RequestBody employee employe) {
        return userRepository.save(employe);
    }

    @PutMapping("/employee/{id}")
    public ResponseEntity<employee> updateuser(@PathVariable(value = "id") Long employeeId,
            @Valid @RequestBody employee employeedetail) throws RelationNotFoundException {
        employee employe = userRepository.findById(employeeId)
                .orElseThrow(() -> new RelationNotFoundException("User not found on ::" + employeeId));
    
        employe.setFirstname(employeedetail.getFirstname());
        employe.setLastname(employeedetail.getLastname());
        final employee updatedemployee = userRepository.save(employe);
        return ResponseEntity.ok(updatedemployee);
    }

    @DeleteMapping("/employee/{id}")
    public Map<String, Boolean> deleteemployee(@PathVariable(value = "id") Long employeeId) throws Exception {
        employee employe = userRepository.findById(employeeId)
                .orElseThrow(() -> new RelationNotFoundException("User not Found on::" + employeeId));
                                         userRepository.delete(employe);
        Map<String, Boolean> response  = new HashMap<>();
        response.put("deleted",Boolean.TRUE);
        return response;
    }
}