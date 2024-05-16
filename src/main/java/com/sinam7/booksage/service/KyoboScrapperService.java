package com.sinam7.booksage.service;

import com.sinam7.booksage.domain.KyoboBookJson;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class KyoboScrapperService extends ScrapperService {


    public KyoboScrapperService(@Qualifier("KyoboApiClient") WebClient webClient) {
        super(webClient);
    }


    @Override
    public KyoboBookJson getBook() {
        return webClient
                .get()
                .uri("/api/gw/pub/pdt/best-seller/total?" +
                        "page=1&per=20&period=002&bsslBksClstCode=A")
                .retrieve()
                .bodyToMono(KyoboBookJson.class)
                .block(REQUEST_TIMEOUT);
    }
}

