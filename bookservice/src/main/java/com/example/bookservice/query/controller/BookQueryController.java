package com.example.bookservice.query.controller;

import com.example.bookservice.command.model.BookRequestModel;
import com.example.bookservice.query.model.BookResponseModel;
import com.example.bookservice.query.queries.GetAllBooksQuery;
import com.example.bookservice.query.queries.GetBooksQuery;
import lombok.AllArgsConstructor;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/v1/books")
@AllArgsConstructor
public class BookQueryController {
    private final QueryGateway queryGateway;

    @GetMapping("/{bookId}")
    public ResponseEntity<BookResponseModel> getBookDetail(@PathVariable(value = "bookId") String bookId){
        GetBooksQuery getBooksQuery = new GetBooksQuery();
        getBooksQuery.setBookId(bookId);
        BookResponseModel bookResponseModel =
                queryGateway.query(getBooksQuery, ResponseTypes.instanceOf(BookResponseModel.class)).join();
        return ResponseEntity.ok(bookResponseModel);
    }

    @GetMapping("/all")
    public ResponseEntity<List<BookResponseModel>> getAllBook(){
        GetAllBooksQuery getAllBooksQuery = new GetAllBooksQuery();
        List<BookResponseModel> list =
                queryGateway.query(getAllBooksQuery, ResponseTypes.multipleInstancesOf(BookResponseModel.class)).join();
        return ResponseEntity.ok(list);
    }
}
