package com.example.bookservice.command.aggregate;

import com.example.bookservice.command.command.CreateBookCommand;
import com.example.bookservice.command.command.DeleteBookCommand;
import com.example.bookservice.command.command.UpdateBookCommand;
import com.example.bookservice.command.event.BookCreateEvent;
import com.example.bookservice.command.event.BookDeleteEvent;
import com.example.bookservice.command.event.BookUpdateEvent;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.modelling.command.TargetAggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

@Aggregate
@NoArgsConstructor
@AllArgsConstructor
public class BookAggregate {

    @AggregateIdentifier
    private String bookId;
    private String name;
    private String author;
    private Boolean isReady;

    //Thinking change status object (not sure change)
    @CommandHandler
    public void handle(CreateBookCommand createBookCommand){
        BookCreateEvent bookCreateEvent = new BookCreateEvent();
        BeanUtils.copyProperties(createBookCommand, bookCreateEvent);
        AggregateLifecycle.apply(bookCreateEvent);
    }

    @CommandHandler
    public void handle(UpdateBookCommand updateBookCommand){
        BookUpdateEvent bookUpdateEvent = new BookUpdateEvent();
        BeanUtils.copyProperties(updateBookCommand, bookUpdateEvent);
        AggregateLifecycle.apply(updateBookCommand);
    }

    @CommandHandler
    public void handle(DeleteBookCommand deleteBookCommand){
        BookDeleteEvent bookDeleteEvent = new BookDeleteEvent();
        BeanUtils.copyProperties(deleteBookCommand, bookDeleteEvent);
        AggregateLifecycle.apply(bookDeleteEvent);
    }
    @EventSourcingHandler
    public void on(BookCreateEvent event){
        this.bookId = event.getBookId();
        this.author = event.getAuthor();
        this.isReady = event.getIsReady();
        this.name = event.getName();
    }

    @EventSourcingHandler
    public void on(BookUpdateEvent event){
        this.bookId = event.getBookId();
        this.author = event.getAuthor();
        this.isReady = event.getIsReady();
        this.name = event.getName();
    }

    @EventSourcingHandler
    public void on(BookDeleteEvent event){
        this.bookId = event.getBookId();
    }
}
