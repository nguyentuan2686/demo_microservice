package com.example.employeeservice.query.model;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class EmployeeResponseModel {
    private String employeeId;
    private String firstname;
    private String lastname;
    private String kin;
    private Boolean isDisciplined;
}
