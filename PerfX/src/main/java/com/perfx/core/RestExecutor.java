package com.perfx.core;

import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.Random;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@Component
@ToString
public class RestExecutor {

    private static final Random rand = new Random();

    @Value("${no.of.threads}")
    private int noOfThreads;
    @Value("${no.of.times}")
    private int noOfTimes;
    @Value("${api.url}")
    private String url;
    @Value("${auth.token}")
    private String token;
    private HttpHeaders headers;

    @Autowired
    private RestTemplate restTemplate;

    private Executor es;

    private HttpEntity<String> requestEntity;

    @PostConstruct
    public void init() {
        headers = new HttpHeaders();
        headers.add("Authorization", token);
        requestEntity = new HttpEntity(headers);
        es = Executors.newFixedThreadPool(noOfThreads);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void execute() {
        for (int i = 0; i < noOfThreads; i++) {
            es.execute(() -> {
                for (int j = 0; j < noOfThreads; j++) {
                    String t = Thread.currentThread().getName();
                    String finalUrl = url.concat(String.format("/%d@talicngpa.com", getRandomNumber()));
                    System.out.println("URL ---- " + finalUrl);
                    ResponseEntity<String> resp = restTemplate.exchange(finalUrl, HttpMethod.GET, requestEntity, String.class);
                    String msg = String.format("Tid = %s, statusCode = %d, body= %s", t, resp.getStatusCodeValue(), resp.getBody());
                    System.out.println(msg);
                    System.out.println("headers " + headers);
                }
            });
        }
    }

    public long getRandomNumber() {
        return rand.nextInt((1000000 - 100000) + 1) + 100000;
    }


}
