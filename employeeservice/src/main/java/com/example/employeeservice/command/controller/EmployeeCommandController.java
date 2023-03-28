package com.example.employeeservice.command.controller;

import com.example.basedomains.dto.Order;
import com.example.basedomains.dto.OrderEvent;
import com.example.employeeservice.command.command.CreateEmployeeCommand;
import com.example.employeeservice.command.command.DeleteEmployeeCommand;
import com.example.employeeservice.command.command.UpdateEmployeeCommand;
import com.example.employeeservice.command.model.EmployeeRequestModel;
import com.example.employeeservice.kafka.EmployeeProducer;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Or;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/employees")
@RequiredArgsConstructor
public class EmployeeCommandController {

    private final CommandGateway commandGateway;

    private final EmployeeProducer producer;

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

    @PostMapping("/send-message")
    public ResponseEntity<String> sendMessage(@RequestBody Order order){
        order.setOrderId(UUID.randomUUID().toString());
        OrderEvent orderEvent = new OrderEvent();
        orderEvent.setStatus("Pending");
        orderEvent.setMessage("Order s");
        orderEvent.setOrder(order);
        producer.sendMessage(orderEvent);
        return ResponseEntity.ok("Sended message");
    }

}
