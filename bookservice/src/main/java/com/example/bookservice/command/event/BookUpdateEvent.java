package com.example.bookservice.command.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class BookUpdateEvent {
    private String bookId;
    private String name;
    private String author;
    private Boolean isReady;
}
