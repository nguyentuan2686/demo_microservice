package com.example.bookservice.command.command;


import lombok.*;
import org.axonframework.modelling.command.TargetAggregateIdentifier;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class CreateBookCommand {

    @TargetAggregateIdentifier
    private String bookId;
    private String name;
    private String author;
    private Boolean isReady;
}
