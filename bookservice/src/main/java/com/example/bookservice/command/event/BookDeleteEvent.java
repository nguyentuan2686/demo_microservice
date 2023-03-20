package com.example.bookservice.command.event;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter
public class BookDeleteEvent {
    private String bookId;
}
