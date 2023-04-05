package com.latentview.assignment.controller;

import com.latentview.assignment.dto.Request;
import com.latentview.assignment.dto.Response;
import com.latentview.assignment.dto.TvShow;
import com.latentview.assignment.entity.UserData;
import com.latentview.assignment.service.RecommendationService;
import com.latentview.assignment.service.UserService;
import jakarta.persistence.GeneratedValue;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/v1/tvShow")
public class RecommendationHistoryController {

    @Autowired
    private RecommendationService recommendationHistoryService;

    @Autowired
    private UserService userservice;

    @GetMapping("genres")
    public Set<String> getGenres(){
        return recommendationHistoryService.getGenreList();
    }

    @GetMapping("allShows")
    public List<TvShow> getAllShows(){
        return recommendationHistoryService.getAllShows();
    }

    @PostMapping("listByGenre")
    public Response getListByGenre(@RequestBody Request req) {
        return this.recommendationHistoryService.recommendTVShow(req);
    }

    @PostMapping("listByRecommended")
    public Response getOnlyRecommended(@RequestBody Request req){
        return this.recommendationHistoryService.onlyRecommended(req);
    }


    @GetMapping("/resetRecommendation")
    public UserData resetRecommendationHistory(@RequestParam Long id){
        return recommendationHistoryService.resetRecommendationsNative(id);
    }



}
