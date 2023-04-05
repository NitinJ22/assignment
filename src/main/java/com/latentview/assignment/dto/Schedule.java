package com.latentview.assignment.dto;

import lombok.Data;

import java.util.List;

@Data
public class Schedule {
    private String time;
    private List<String> days;
}
