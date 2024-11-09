package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.TaxDetails;
import com.example.demo.entity.Employee;
import com.example.demo.service.EmployeeService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<Employee> createEmployee(@RequestBody @Valid Employee employee) {
        Employee savedEmployee = employeeService.saveEmployee(employee);
        return ResponseEntity.ok(savedEmployee);
    }

    @GetMapping("/{id}/tax")
    public ResponseEntity<TaxDetails> getEmployeeTaxDetails(@PathVariable String id) {
        TaxDetails taxDetails = employeeService.calculateTax(id);
        return ResponseEntity.ok(taxDetails);
    }
}