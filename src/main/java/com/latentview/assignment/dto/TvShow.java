package com.latentview.assignment.dto;

import lombok.Data;

import java.util.List;

@Data
public class TvShow {
    private long id;
    private String url;
    private String name;
    private String type;
    private String language;
    private List<String> genres;
    private String status;
    private int runtime;
    private int averageRuntime;
    private String premiered;
    private String ended;
    private String officialSite;
    private Schedule schedule;
    private Rating rating;
    private int weight;
    private Network network;
    private WebChannel webChannel;
    private Country dvdCountry;
    private Externals externals;
    private Image image;
    private String summary;
    private Double updated;
    private Links _links;
}
