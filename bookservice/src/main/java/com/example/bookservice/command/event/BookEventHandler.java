package com.example.bookservice.command.event;

import com.example.bookservice.command.data.Book;
import com.example.bookservice.command.data.BookRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BookEventHandler {
    private final BookRepository bookRepository;

    //After command success -> Event save to DB
    @EventHandler
    public void on(BookCreateEvent event){
        Book book = new Book();
        BeanUtils.copyProperties(event, book);
        bookRepository.save(book);
    }
}
