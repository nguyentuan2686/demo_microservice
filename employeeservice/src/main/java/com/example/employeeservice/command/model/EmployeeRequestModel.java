package com.example.employeeservice.command.model;


import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class EmployeeRequestModel {

    private String employeeId;
    private String firstname;
    private String lastname;
    private String kin;
    private Boolean isDisciplined;
}
