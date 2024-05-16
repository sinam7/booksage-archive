package com.sinam7.booksage.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class InterparkBookJson implements BookJson {

    private String imageSrc;
    private String link;
    private String title;
    private String author;
    private String company;
    private String price;

}
