package com.example.bookservice.command.controller;

import com.example.bookservice.command.command.CreateBookCommand;
import com.example.bookservice.command.command.DeleteBookCommand;
import com.example.bookservice.command.command.UpdateBookCommand;
import com.example.bookservice.command.model.BookRequestModel;
import lombok.AllArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/books")
@AllArgsConstructor
public class BookCommandController {

    private final CommandGateway commandGateway;

    @PostMapping
    public ResponseEntity<String> addBook(@RequestBody BookRequestModel model) {
        CreateBookCommand command = CreateBookCommand
                .builder()
                .bookId(UUID.randomUUID().toString())
                .author(model.getAuthor())
                .name(model.getName())
                .isReady(true)
                .build();
        commandGateway.sendAndWait(command);
        return ResponseEntity.ok("Created");
    }

    @PutMapping
    public ResponseEntity<String> updateBook(@RequestBody BookRequestModel model){
        UpdateBookCommand updateBookCommand = UpdateBookCommand
                .builder()
                .bookId(model.getBookId())
                .author(model.getAuthor())
                .name(model.getName())
                .isReady(model.getIsReady())
                .build();
        commandGateway.sendAndWait(updateBookCommand);
        return ResponseEntity.ok("Updated");
    }

    @DeleteMapping("/{bookId}")
    public ResponseEntity<String> deleteBook(@PathVariable(value = "bookId") String bookId){
        DeleteBookCommand command = DeleteBookCommand
                .builder()
                .bookId(bookId)
                .build();
        commandGateway.sendAndWait(command);
        return ResponseEntity.ok("Deleted");
    }
}
