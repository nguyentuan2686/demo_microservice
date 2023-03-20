package com.example.bookservice.command.command;

import lombok.*;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DeleteBookCommand {
    @TargetAggregateIdentifier
    private String bookId;
}
