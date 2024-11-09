package com.example.demo.dto;

import java.math.BigDecimal;

public class TaxDetails {
    private String employeeId;
    private String firstName;
    private String lastName;
    private BigDecimal yearlySalary;
    private double taxAmount;
    private double cessAmount;

    public TaxDetails(String employeeId, String firstName, String lastName, double yearlySalary, double taxAmount, double cessAmount) {
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.yearlySalary =  BigDecimal.valueOf(yearlySalary);
        this.taxAmount = taxAmount;
        this.cessAmount = cessAmount;
    }

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	

	public BigDecimal getYearlySalary() {
		return yearlySalary;
	}

	public void setYearlySalary(BigDecimal yearlySalary) {
		this.yearlySalary = yearlySalary;
	}

	public double getTaxAmount() {
		return taxAmount;
	}

	public void setTaxAmount(double taxAmount) {
		this.taxAmount = taxAmount;
	}

	public double getCessAmount() {
		return cessAmount;
	}

	public void setCessAmount(double cessAmount) {
		this.cessAmount = cessAmount;
	}
    
    
    
}