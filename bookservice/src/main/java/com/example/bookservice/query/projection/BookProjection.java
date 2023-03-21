package com.example.bookservice.query.projection;

import com.example.bookservice.command.data.Book;
import com.example.bookservice.command.data.BookRepository;
import com.example.bookservice.query.model.BookResponseModel;
import com.example.bookservice.query.queries.GetAllBooksQuery;
import com.example.bookservice.query.queries.GetBooksQuery;
import lombok.AllArgsConstructor;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class BookProjection {
    private final BookRepository bookRepository;

    @QueryHandler
    public BookResponseModel handle(GetBooksQuery getBooksQuery){
        BookResponseModel model = new BookResponseModel();
        Book book = bookRepository.getReferenceById(getBooksQuery.getBookId());
        BeanUtils.copyProperties(book, model);
        return model;
    }

    @QueryHandler
    public List<BookResponseModel> handle(GetAllBooksQuery getAllBooksQuery){
        List<BookResponseModel> modelList = new ArrayList<>();
        List<Book> list = bookRepository.findAll();
        list.forEach(s -> {
            BookResponseModel model = new BookResponseModel();
            BeanUtils.copyProperties(s, model);
            modelList.add(model);
        });
        return modelList;
    }
}
