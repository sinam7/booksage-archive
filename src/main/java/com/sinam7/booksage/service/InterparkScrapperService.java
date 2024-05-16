package com.sinam7.booksage.service;

import com.sinam7.booksage.domain.InterparkBookJson;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;
import java.nio.charset.StandardCharsets;

@Service
public class InterparkScrapperService extends ScrapperService {


    private final String origin = "https://book.interpark.com";
    private final String pathquery = "/display/collectlist.do?_method=BestsellerHourNew201605&bestTp=1&dispNo=028";

    public InterparkScrapperService() {
        super(null);
    }


    @Override
    public InterparkBookJson getBook() {
        String html = getHtml(origin + pathquery);
        Elements select = Jsoup.parse(html).select("div.rankBestContentList ol li div.listItem");

        for (Element e : select) {
            String imageSrc = e.select("div.coverImage img").attr("src");
            String link = e.select("div.coverImage label a").attr("href");
            String title = e.select("div.itemName strong").html();
            String author = e.select("div.itemMeta span.author").html();
            String company = e.select("div.itemMeta span.company").html();
            String price = e.select("div.priceWrap span.price").html();

//            StandardCharsets.E;
            // encoding windows-1252 ansi -> euc-kr

            InterparkBookJson.builder()
                    .title(title)
                    .author(author)
                    .company(company)
                    .price(price)
                    .imageSrc(imageSrc)
                    .link(link);

        }

        return null;
    }



    public String getHtml(String url) {

        try {
            URL targetUrl = new URI(url).toURL();
            BufferedReader reader = new BufferedReader(new InputStreamReader(targetUrl.openStream(), StandardCharsets.UTF_8));
            StringBuilder html = new StringBuilder();

            String current = "";
            while ((current = reader.readLine()) != null) {
                html.append(current);
            }

            reader.close();

            return html.toString();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

