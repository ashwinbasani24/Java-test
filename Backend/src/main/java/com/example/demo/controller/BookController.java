package com.example.demo.controller;

import com.example.demo.model.Book;
import com.example.demo.model.Library;
import com.example.demo.repository.BookRepository;
import com.example.demo.repository.LibraryRepository;
import com.example.demo.service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class BookController {


     @Autowired
     private LibraryService libraryService;


    /**
     * api to create book
     * @param libraryId
     * @param book
     * @return
     */
    @PostMapping("/library/{libraryId}/books")
    public ResponseEntity<List<Book>> createBook(@PathVariable(value = "libraryId") Long libraryId,
                                                 @RequestBody Book book) {
        try {
            Library _library = libraryService.createBook(libraryId, book);
            return new ResponseEntity<>(_library.getBooks(), HttpStatus.CREATED);
        } catch (Exception e) {
            System.out.println("e---"+e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    /**
     * api to update  book
     * @param libraryId
     * @param book
     * @return
     */
    @PutMapping("/library/{libraryId}/books")
    public ResponseEntity<Book> updateBook(@PathVariable(value = "libraryId") Long libraryId,
                                           @RequestBody Book book){
        try {
            Book _savedBook = libraryService.updateBook(libraryId, book);
            return new ResponseEntity<>(_savedBook, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/books/{bookId}")
    public ResponseEntity<Book> getBook(@PathVariable(value = "bookId") Long bookId){
        return new ResponseEntity<>(libraryService.getBook(bookId),HttpStatus.OK);
    }

    @DeleteMapping("/books/{bookId}")
    public ResponseEntity<HttpStatus> deleteBook(@PathVariable(value = "bookId") Long bookId){
         libraryService.deleteBook(bookId);
         return new ResponseEntity<>(HttpStatus.OK);
    }


}