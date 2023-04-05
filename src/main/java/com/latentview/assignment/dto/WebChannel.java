package com.latentview.assignment.dto;

import lombok.Data;

@Data
public class WebChannel {
    private int id;
    private String name;
    private Country country;
    private String officialSite;
}
