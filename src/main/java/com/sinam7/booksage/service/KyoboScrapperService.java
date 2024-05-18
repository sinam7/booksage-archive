package com.sinam7.booksage.service;

import com.sinam7.booksage.domain.KyoboBook;
import com.sinam7.booksage.domain.KyoboBookRawJson;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class KyoboScrapperService extends ScrapperService {

    private static final String origin = "https://search.kyobobook.co.kr";
    private final WebClient kyoboApiProductClient;


    public KyoboScrapperService(@Qualifier("KyoboApiProductClient") WebClient kyoboApiProductClient) {
        this.kyoboApiProductClient = kyoboApiProductClient;
    }


    @Override
    public List<KyoboBook> getBooks() {
        KyoboBookRawJson raw = kyoboApiProductClient
                .get()
                .uri("/api/gw/pub/pdt/best-seller/total?" +
                        "page=1&per=20&period=002&bsslBksClstCode=A")
                .retrieve()
                .bodyToMono(KyoboBookRawJson.class)
                .block(REQUEST_TIMEOUT);
        assert raw != null;
        List<KyoboBookRawJson.KyoboBookJson> kyoboBookJsons = raw.getData().getKyoboBookJson();
        ArrayList<KyoboBook> res = new ArrayList<>();
        for (KyoboBookRawJson.KyoboBookJson json : kyoboBookJsons) res.add(KyoboBook.build(json));
        return res;
    }

    @Override
    public List<KyoboBook> searchBook(String query) {
        String pathquery = "/search?keyword="+query+"&gbCode=TOT&target=total";

        Elements select;
        try {
            Document doc = Jsoup.connect(origin + pathquery).header("Content-Type", "text/html; charset=UTF-8").get();
            Thread.sleep(1000);
            select = doc.select("li.prod_item"); //
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }

        ArrayList<KyoboBook> kyoboBooks = new ArrayList<>();

        for (Element e : select) {
            String bid = e.select("input.result_checkbox").attr("data-bid");
            String imageSrc = "https://contents.kyobobook.co.kr/sih/fit-in/200x0/pdt/"+bid+".jpg";
            String link = e.select("a.prod_link").attr("href");
            Elements info = e.select("div.prod_info_box");
            String title = info.select("div.prod_name_group span").eachText().get(1);
            String author = info.select("div.prod_author_info a.author").html().replace("\n", ", 역 ");
            String company = info.select("div.prod_author_info div.prod_publish a.text").html();
            Elements priceEle = e.select("div.prod_price span.price");
            String price = priceEle.select("span.val").html() + priceEle.select("span.unit").html().replaceAll("&nbsp;", "");

            kyoboBooks.add(new KyoboBook(title, author, company, price, link, imageSrc));

        }

        return kyoboBooks;

    }
}

