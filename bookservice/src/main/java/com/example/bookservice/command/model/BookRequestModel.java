package com.example.bookservice.command.model;


import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BookRequestModel {
    private String bookId;
    private String name;
    private String author;
    private Boolean isReady;
}
