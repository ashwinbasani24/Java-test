package com.example.demo.service;

import com.example.demo.model.Book;
import com.example.demo.model.Library;
import com.example.demo.repository.BookRepository;
import com.example.demo.repository.LibraryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class LibraryService {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    LibraryRepository libraryRepository;

    public Library createBook(Long libraryId, Book book){

        Library _library = null;
        Optional<Library> _libraryOPtional = libraryRepository.findById(libraryId);
        if(_libraryOPtional.isPresent()){
            Library library = _libraryOPtional.get();
            library.getBooks().add(book);
            _library = libraryRepository.save(library);
        }
        return _library;

    }

    public Book updateBook(Long libraryId,Book book){
        Book _savedBook=null;
        Optional<Book> byIdBook = bookRepository.findById(book.getId());
        if(byIdBook.isPresent()){
            Book _dbbook= byIdBook.get();
            _dbbook.setDescription(book.getDescription());
            _dbbook.setTitle(book.getTitle());
            _dbbook.setPublished(book.isPublished());
            _savedBook = bookRepository.save(_dbbook);
        }
        return _savedBook;
    }

    public Book getBook(Long bookId){
       return bookRepository.findById(bookId).get();
    }

    public void deleteBook(Long bookId){
        bookRepository.delete(getBook(bookId));
    }
     public Library createLibrary(Library library){
         return libraryRepository.save(library);
     }

     public List<Library> getAllLibraries(){
         return libraryRepository.findAll();
     }

     public Library getLibrary(Long libraryId){
        return libraryRepository.findById(libraryId).get();
     }

     public void deleteLibrary(Long libraryId){
         libraryRepository.delete(getLibrary(libraryId));
     }
}
