package com.example.demo.controller;

import com.example.demo.model.Book;
import com.example.demo.model.Library;
import com.example.demo.service.LibraryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@RunWith(SpringRunner.class)
@WebMvcTest(value = BookController.class)
@WithMockUser
public class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LibraryService libraryService;

    Book mockBook= new Book(2,"Book8library","Book8libraryDesc",false);

    @Test
    public void getBook() throws  Exception {
        Mockito.when(libraryService.getBook(Mockito.anyLong())).thenReturn(mockBook);
        MockHttpServletRequestBuilder accept = MockMvcRequestBuilders.get("/api/books/2").accept(MediaType.APPLICATION_JSON);
        MvcResult mvcResult = mockMvc.perform(accept).andReturn();
        System.out.println(mvcResult.getResponse());
        String expected="{id:2,title:Book8library,description:Book8libraryDesc,published:false}";
        JSONAssert.assertEquals(expected,mvcResult.getResponse().getContentAsString(),false);
    }



}
