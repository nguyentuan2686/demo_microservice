package com.example.employeeservice.command.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeUpdateEvent {

    private String employeeId;
    private String firstname;
    private String lastname;
    private String kin;
    private Boolean isDisciplined;
}
