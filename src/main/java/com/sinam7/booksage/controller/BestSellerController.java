package com.sinam7.booksage.controller;

import com.sinam7.booksage.domain.Book;
import com.sinam7.booksage.service.ScrapperService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookstore")
@RequiredArgsConstructor
public class BestSellerController {

    private final List<ScrapperService> scrapperServiceList;

    @GetMapping("/{storeId}")
    public List<Book> findAll(@PathVariable("storeId") Integer id) {
        return (List<Book>) scrapperServiceList.get(id).getBooks();
    }

    @GetMapping("/{storeId}/search")
    public List<Book> search(@PathVariable("storeId") Integer id, @RequestParam("query") String query) {
        return (List<Book>) scrapperServiceList.get(id).searchBook(query);
    }







}
