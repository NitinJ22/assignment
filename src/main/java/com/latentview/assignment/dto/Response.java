package com.latentview.assignment.dto;

import com.latentview.assignment.entity.UserData;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Response {
    private List<TvShow> list;
    private int listSize;
    private UserData user;
}
