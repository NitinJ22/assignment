package com.latentview.assignment.dto;

import lombok.Data;

@Data
public class Network {
    private int id;
    private String name;
    private Country country;
    private String officialSite;
}
