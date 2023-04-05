package com.latentview.assignment.dto;

import lombok.Data;

@Data
public class Request {
    private Long id;
    private String genre;
    private int startIndex;
    private int endIndex;
}
