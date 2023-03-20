package com.example.bookservice.command.model;


import lombok.*;
import org.checkerframework.checker.units.qual.A;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BooKRequestModel {
    private String bookId;
    private String name;
    private String author;
    private Boolean isReady;
}
