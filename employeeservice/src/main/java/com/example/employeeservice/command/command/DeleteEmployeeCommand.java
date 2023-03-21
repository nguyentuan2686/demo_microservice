package com.example.employeeservice.command.command;

import lombok.*;
import org.axonframework.modelling.command.TargetAggregateIdentifier;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DeleteEmployeeCommand {
    @TargetAggregateIdentifier
    private String employeeId;
}
