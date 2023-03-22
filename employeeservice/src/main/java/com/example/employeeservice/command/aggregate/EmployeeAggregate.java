package com.example.employeeservice.command.aggregate;

import com.example.employeeservice.command.command.CreateEmployeeCommand;
import com.example.employeeservice.command.command.DeleteEmployeeCommand;
import com.example.employeeservice.command.command.UpdateEmployeeCommand;
import com.example.employeeservice.command.event.EmployeeCreateEvent;
import com.example.employeeservice.command.event.EmployeeDeleteEvent;
import com.example.employeeservice.command.event.EmployeeUpdateEvent;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

@Aggregate
@NoArgsConstructor
public class EmployeeAggregate {

    @AggregateIdentifier
    private String employeeId;
    private String firstname;
    private String lastname;
    private String kin;
    private Boolean isDisciplined;

    @CommandHandler
    public EmployeeAggregate(CreateEmployeeCommand createEmployeeCommand){
        EmployeeCreateEvent event = new EmployeeCreateEvent();
        BeanUtils.copyProperties(createEmployeeCommand, event);
        AggregateLifecycle.apply(event);
    }

    @CommandHandler
    public void handle(UpdateEmployeeCommand updateEmployeeCommand){
        EmployeeUpdateEvent event = new EmployeeUpdateEvent();
        BeanUtils.copyProperties(updateEmployeeCommand, event);
        AggregateLifecycle.apply(event);
    }

    @CommandHandler
    public void handle(DeleteEmployeeCommand deleteEmployeeCommand){
        EmployeeDeleteEvent event = new EmployeeDeleteEvent();
        BeanUtils.copyProperties(deleteEmployeeCommand, event);
        AggregateLifecycle.apply(event);
    }

    @EventSourcingHandler
    public void onCreate(EmployeeCreateEvent event){
        this.employeeId = event.getEmployeeId();
        this.firstname = event.getFirstname();
        this.lastname = event.getLastname();
        this.kin = event.getKin();
        this.isDisciplined = event.getIsDisciplined();
    }

    @EventSourcingHandler
    public void onUpdate(EmployeeUpdateEvent event){
        this.employeeId = event.getEmployeeId();
        this.firstname = event.getFirstname();
        this.lastname = event.getLastname();
        this.kin = event.getKin();
        this.isDisciplined = event.getIsDisciplined();
    }

    @EventSourcingHandler
    public void onDelete(EmployeeDeleteEvent event){
        this.employeeId = event.getEmployeeId();
    }
}
