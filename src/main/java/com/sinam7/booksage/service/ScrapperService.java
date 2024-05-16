package com.sinam7.booksage.service;

import com.sinam7.booksage.domain.BookJson;
import com.sinam7.booksage.domain.KyoboBookJson;
import lombok.RequiredArgsConstructor;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.Duration;

@RequiredArgsConstructor
public abstract class ScrapperService {

    protected final WebClient webClient;
    protected static final Duration REQUEST_TIMEOUT = Duration.ofSeconds(3);

    public abstract BookJson getBook();

}
