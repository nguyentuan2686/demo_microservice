package com.example.employeeservice.command.controller;

import com.example.employeeservice.command.command.CreateEmployeeCommand;
import com.example.employeeservice.command.command.DeleteEmployeeCommand;
import com.example.employeeservice.command.command.UpdateEmployeeCommand;
import com.example.employeeservice.command.model.EmployeeRequestModel;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/employee")
@AllArgsConstructor
@RequiredArgsConstructor
public class EmployeeCommandController {

    private final CommandGateway commandGateway;


    @PostMapping
    public ResponseEntity<String> addEmployee(@RequestBody EmployeeRequestModel model) {
        CreateEmployeeCommand command = CreateEmployeeCommand
                .builder()
                .employeeId(UUID.randomUUID().toString())
                .firstname(model.getFirstname())
                .lastname(model.getLastname())
                .kin(model.getKin())
                .isDisciplined(false)
                .build();

        commandGateway.sendAndWait(command);

        return ResponseEntity.ok("Created");
    }
    @PutMapping
    public ResponseEntity<String> updateEmployee(@RequestBody EmployeeRequestModel model) {
        UpdateEmployeeCommand command = UpdateEmployeeCommand
                .builder()
                .employeeId(model.getEmployeeId())
                .firstname(model.getFirstname())
                .lastname(model.getLastname())
                .kin(model.getKin())
                .isDisciplined(model.getIsDisciplined())
                .build();
        commandGateway.sendAndWait(command);
        return ResponseEntity.ok("Updated");
    }
    @DeleteMapping("/{employeeId}")
    public ResponseEntity<String> deleteEmployee(@PathVariable String employeeId) {
        DeleteEmployeeCommand command = DeleteEmployeeCommand
                .builder()
                .employeeId(employeeId)
                .build();
        commandGateway.sendAndWait(command);
        return ResponseEntity.ok("Deleted");
    }

}
