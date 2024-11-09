package com.example.demo.service;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.TaxDetails;
import com.example.demo.entity.Employee;
import com.example.demo.repository.EmployeeRepository;

@Service
	public class EmployeeService {

	    @Autowired
	    private EmployeeRepository employeeRepository;

	    public Employee saveEmployee(Employee employee) {
	        return employeeRepository.save(employee);
	    }

	    public TaxDetails calculateTax(String employeeId) {
	        Employee employee = employeeRepository.findById(employeeId)
	                .orElseThrow(() -> new IllegalArgumentException("Employee not found"));
	        
	        double annualSalary = calculateAnnualSalary(employee.getMonthlySalary(),employee.getDateOfJoining());
	        double taxAmount = calculateTaxAmount(annualSalary);
	        double cessAmount = calculateCess(annualSalary);

	        return new TaxDetails(String.valueOf(employee.getId()), employee.getFirstName(), employee.getLastName(), annualSalary, taxAmount, cessAmount);
	    }

	  
	   
	    private double calculateAnnualSalary(double monthlySalary, String dateOfJoining) {
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	        LocalDate joiningDate = LocalDate.parse(dateOfJoining, formatter);

	        LocalDate startOfYear = LocalDate.of(joiningDate.getYear(), Month.APRIL, 1);
	        LocalDate currentEnd = LocalDate.now().isBefore(LocalDate.of(joiningDate.getYear() + 1, Month.MARCH, 31))
	                               ? LocalDate.now()
	                               : LocalDate.of(joiningDate.getYear() + 1, Month.MARCH, 31);

	        if (joiningDate.isAfter(currentEnd)) {
	            return 0; 
	        }

	        long monthsWorked = ChronoUnit.MONTHS.between(
	                            joiningDate.isAfter(startOfYear) ? joiningDate : startOfYear,
	                            currentEnd) + 1;
	        
	        return monthlySalary * monthsWorked;
	    }

	    private double calculateTaxAmount(double annualSalary) {
	        double tax = 0;
	        if (annualSalary > 1000000) {
	            tax += (annualSalary - 1000000) * 0.20;
	            annualSalary = 1000000;
	        }
	        if (annualSalary > 500000) {
	            tax += (annualSalary - 500000) * 0.10;
	            annualSalary = 500000;
	        }
	        if (annualSalary > 250000) {
	            tax += (annualSalary - 250000) * 0.05;
	        }
	        return tax;
	    }

	    private double calculateCess(double annualSalary) {
	        if (annualSalary > 2500000) {
	            return (annualSalary - 2500000) * 0.02;
	        }
	        return 0;
	    }
	}
	

