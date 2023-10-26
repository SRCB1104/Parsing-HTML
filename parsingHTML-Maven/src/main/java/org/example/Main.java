package org.example;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    public static void main(String[] args) {
        String url = "https://people.sc.fsu.edu/~jburkardt/data/csv/csv.html";

        try {
            Document documento = Jsoup.connect(url).get();
            Elements links = documento.select("a[href$=.csv]");
            ExecutorService executorService = Executors.newFixedThreadPool(links.size());

            for (Element link : links) {
                String csvLink = link.absUrl("href");
                String csvNombre = link.text();
                executorService.execute(new Procesador(csvLink, csvNombre));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
