package com.example.bookservice.command.controller;

import com.example.bookservice.command.command.CreateBookCommand;
import com.example.bookservice.command.model.BooKRequestModel;
import lombok.AllArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/books")
@AllArgsConstructor
public class BookCommandController {

    private final CommandGateway commandGateway;

    @PostMapping
    public ResponseEntity<String> addBook(@RequestBody BooKRequestModel model) {
        CreateBookCommand command = CreateBookCommand
                .builder()
                .bookId(UUID.randomUUID().toString())
                .author(model.getAuthor())
                .name(model.getName())
                .isReady(true)
                .build();
        commandGateway.sendAndWait(command);
        return ResponseEntity.ok("Create done");
    }
}
