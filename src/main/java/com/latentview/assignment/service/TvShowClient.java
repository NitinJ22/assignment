package com.latentview.assignment.service;

import com.latentview.assignment.dto.TvShow;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class TvShowClient {

    private final String baseUrl = "https://api.tvmaze.com/";

    public Mono<List<TvShow>> getAllShowData(){

        Flux<TvShow> showData = buildWebClient().get()
                .uri("shows")
                .retrieve()
                .bodyToFlux(TvShow.class);

        Mono<List<TvShow>> showDataList = showData.collectList();

//        tvShowListMono.subscribe(tvShows -> {
//            System.out.println("TV Shows: " + tvShows);
//        });
        return showDataList;
    }

    private WebClient buildWebClient(){
        return WebClient.builder()
                .baseUrl(baseUrl)
                .build();
    }


    public TvShow getTVShowById(int id) {
        return buildWebClient().get()
                .uri("/shows/" + id)
                .retrieve()
                .bodyToMono(TvShow.class)
                .block();
    }

}
