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

@CrossOrigin
@RestController
@RequestMapping("/api")
public class LibraryController {

    @Autowired
    private LibraryService libraryService;


    /**
     * api to create library
     * @param library
     * @return
     */
    @PostMapping("/library")
    public ResponseEntity<Library> createLibrary(@RequestBody Library library){
        try{
            return  new ResponseEntity<>(libraryService.createLibrary(library),HttpStatus.CREATED);
        }catch(Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/library")
    public ResponseEntity<List<Library>> getAllLibraries(){
        return new ResponseEntity<>(libraryService.getAllLibraries(),HttpStatus.OK) ;
    }

    /**
     * Api to get all the books for a library
     * @param libraryId
     * @return
     */
    @GetMapping("/library/{libraryId}")
    public ResponseEntity<Library> getLibrary(@PathVariable(value = "libraryId") Long libraryId){
        return new ResponseEntity<>(libraryService.getLibrary(libraryId),HttpStatus.OK) ;
    }

    /**
     * api to delete
     * @param libraryId
     * @return
     */
    @DeleteMapping("/library/{libraryId}")
    public ResponseEntity<HttpStatus> deleteLibrary(@PathVariable(value = "libraryId") Long libraryId){
          libraryService.deleteLibrary(libraryId);
          return new ResponseEntity<>(HttpStatus.OK) ;
    }






}
