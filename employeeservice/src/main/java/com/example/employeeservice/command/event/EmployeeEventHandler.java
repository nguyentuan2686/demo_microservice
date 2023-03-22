package com.example.employeeservice.command.event;

import com.example.employeeservice.command.data.Employee;
import com.example.employeeservice.command.data.EmployeeRepository;
import jakarta.ws.rs.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmployeeEventHandler {

    private final EmployeeRepository employeeRepository;

    @EventHandler
    public void createEmployee(EmployeeCreateEvent event){
        Employee employee = new Employee();
        BeanUtils.copyProperties(event, employee);
        employeeRepository.save(employee);
    }

    @EventHandler
    public void updateEmployee(EmployeeUpdateEvent event){
        Employee employee = findEmployee(event.getEmployeeId());
        employee.setFirstname(event.getFirstname());
        employee.setLastname(event.getLastname());
        employee.setKin(event.getKin());
        employee.setIsDisciplined(event.getIsDisciplined());
        employeeRepository.save(employee);
    }

    @EventHandler
    public void deleteEmployee(EmployeeDeleteEvent event){
        Employee employee = findEmployee(event.getEmployeeId());
        employeeRepository.deleteById(event.getEmployeeId());
    }

    public Employee findEmployee(String id){
        return employeeRepository.findById(id).orElseThrow(()-> new NotFoundException("No employee match"));
    }
}
