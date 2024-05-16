package com.sinam7.booksage.controller;

import com.sinam7.booksage.domain.BookJson;
import com.sinam7.booksage.service.ScrapperService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/bookstore")
@RequiredArgsConstructor
public class BestSellerController {

    private final List<ScrapperService> scrapperServiceList;

    @GetMapping("/{storeId}")
    public BookJson get(@PathVariable("storeId") Integer id) {
        return scrapperServiceList.get(id).getBook();
    }





}
