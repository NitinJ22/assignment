package com.latentview.assignment.dto;

import lombok.Data;

@Data
public class Links {
    private LinkHref self;
    private LinkHref previousEpisode;

    private LinkHref nextEpisode;
}
